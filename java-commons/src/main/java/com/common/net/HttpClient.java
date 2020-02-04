package com.common.net;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * 引用自 nacos 源码中的 com.alibaba.nacos.client.naming.net.HttpClient , 改编了一部分
 *
 * @author nkorange
 */
public final class HttpClient {

    /**
     * 编码方式  可以引用 {@link StandardCharsets}
     */
    public static final String UTF_8 = "UTF-8";


    /**
     * 读超时时间
     */
    public static final int TIME_OUT_MILLIS = Integer
            .getInteger("com.net.ctimeout", 30000);

    /**
     * 连接超时时间
     */
    public static final int CON_TIME_OUT_MILLIS = Integer
            .getInteger("com.net.ctimeout", 3000);

    /**
     * 是否支持 https, 默认是http , 若是https需要改成 true
     */
    public static final boolean ENABLE_HTTPS = Boolean
            .getBoolean("com.net.cenable");


    /**
     * 8种请求方式
     */
    // GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String GET = "GET";
    public static final String DELETE = "DELETE";
    public static final String HEAD = "HEAD";
    public static final String PATCH = "PATCH";
    public static final String OPTIONS = "OPTIONS";
    public static final String TRACE = "TRACE";

    // 请求体中需要放入的
    public static final Set<String> BODY_METHOD = new HashSet<>();

    static {
        // 需要将参数放入到放入请求体的
        BODY_METHOD.add(POST);
        BODY_METHOD.add(PUT);
        BODY_METHOD.add(TRACE);

        // limit max redirection  最大重定向次数
        System.setProperty("http.maxRedirects", "5");
    }


    public static String getPrefix() {
        if (ENABLE_HTTPS) {
            return "https://";
        }
        return "http://";
    }


    public static HttpResult httpGet(String url) {
        return httpGet(url, Collections.emptyMap(), Collections.emptyMap(), UTF_8);
    }

    public static HttpResult httpGet(String url, Map<String, String> headers, Map<String, String> paramValues, String encoding) {
        return request(url, headers, paramValues, encoding, GET);
    }

    public static HttpResult httpGet(String url, Map<String, String> paramValues) {
        return request(url, Collections.emptyMap(), paramValues, UTF_8, GET);
    }

    public static HttpResult httpPost(String url, Map<String, String> headers, Map<String, String> paramValues, String encoding) {
        return request(url, headers, paramValues, encoding, POST);
    }


    public static HttpResult httpPost(String url, Map<String, String> paramValues) {
        return httpPost(url, Collections.emptyMap(), paramValues, UTF_8);
    }


    public static HttpResult httpPut(String url, Map<String, String> headers, Map<String, String> paramValues, String encoding) {
        return request(url, headers, paramValues, encoding, PUT);
    }

    public static HttpResult httpPut(String url, Map<String, String> paramValues) {
        return httpPut(url, Collections.emptyMap(), paramValues, UTF_8);
    }


    /**
     * 不支持  multipart/form-data 文件上传
     * 默认使用的是  application/x-www-form-urlencoded , 也推荐使用
     */
    public static HttpResult request(String url, Map<String, String> headers, Map<String, String> paramValues, String encoding, String method) {
        HttpURLConnection conn = null;
        try {
            // a=1&b=1
            String encodedContent = encodingParams(paramValues, encoding);

            // 不是 post/put 就拼接在url中
            if (!BODY_METHOD.contains(method)) {
                url += (encodedContent == null || encodedContent.length() == 0) ? "" : ("?" + encodedContent);
            }

            conn = (HttpURLConnection) new URL(url).openConnection();

            setHeaders(conn, headers, encoding);

            conn.setConnectTimeout(CON_TIME_OUT_MILLIS);

            conn.setReadTimeout(TIME_OUT_MILLIS);

            conn.setRequestMethod(method);

            conn.setDoOutput(true);

            // post / put 方式 - 写请求体
            if (BODY_METHOD.contains(method)) {
                // fix: apache http nio framework must set some content to request body
                byte[] b = encodedContent.getBytes();
                conn.setRequestProperty("Content-Length", String.valueOf(b.length));
                conn.getOutputStream().write(b, 0, b.length);
                conn.getOutputStream().flush();
                conn.getOutputStream().close();
                b = null;
            }
            conn.connect();
            return getResult(conn);
        } catch (Exception e) {
            return new HttpResult(500, e.toString(), Collections.emptyMap());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private static final String encodingGzip = "gzip";

    /**
     * 获取结果
     *
     * @param conn
     * @return
     * @throws IOException
     */
    private static HttpResult getResult(HttpURLConnection conn) throws IOException {
        int respCode = conn.getResponseCode();
        InputStream inputStream = null;
        Map<String, String> respHeaders = null;
        try {
            // 400 以上全部为错误
            if (respCode >= 400) {
                inputStream = conn.getErrorStream();
            } else {
                // 其他为正确
                inputStream = conn.getInputStream();
            }
            respHeaders = new HashMap<>(conn.getHeaderFields().size());
            for (Map.Entry<String, List<String>> entry : conn.getHeaderFields().entrySet()) {
                respHeaders.put(entry.getKey(), entry.getValue().get(0));
            }

            if (encodingGzip.equals(respHeaders.get(HttpHeaders.CONTENT_ENCODING))) {
                inputStream = new GZIPInputStream(inputStream);
            }
            return new HttpResult(respCode, toString(inputStream, getCharset(conn)), respHeaders);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private static final String EMPTY = "";

    private static String toString(InputStream input, String encoding) {
        try {
            return (null == encoding) ? toString(new InputStreamReader(input, UTF_8))
                    : toString(new InputStreamReader(input, encoding));
        } catch (Exception e) {
            return EMPTY;
        }
    }


    private static String toString(Reader reader) throws IOException {
        CharArrayWriter sw = new CharArrayWriter();
        copy(reader, sw);
        return sw.toString();
    }


    private static void copy(Reader input, Writer output) throws IOException {
        char[] buffer = new char[1 << 12];
        for (int n = 0; (n = input.read(buffer)) >= 0; ) {
            output.write(buffer, 0, n);
        }
    }


    private static String getCharset(HttpURLConnection conn) {
        String contentType = conn.getContentType();
        if (contentType==null||contentType.length()==0) {
            return UTF_8;
        }

        String[] values = contentType.split(";");
        if (values.length == 0) {
            return UTF_8;
        }

        String charset = UTF_8;
        for (String value : values) {
            value = value.trim();

            if (value.toLowerCase().startsWith("charset=")) {
                charset = value.substring("charset=".length());
            }
        }
        return charset;
    }

    /**
     * 设置请求头参数
     */
    private static final String DEFAULT_CONTENT_TYPE = "application/x-www-form-urlencoded;charset=";

    private static void setHeaders(HttpURLConnection conn, Map<String, String> headers, String encoding) {
        if (null != headers && !headers.isEmpty()) {
            // 设置头部 CONTENT_TYPE
            headers.compute(HttpHeaders.CONTENT_TYPE, (s, s2) -> {
                if (s2 == null || s2.length() == 0) {
                    return DEFAULT_CONTENT_TYPE + encoding;
                }
                return s2;
            });


            // 设置编解码
            headers.compute(HttpHeaders.ACCEPT_CHARSET, (s, s2) -> {
                if (s2 == null || s2.length() == 0) {
                    return encoding;
                }
                return s2;
            });

            headers.forEach((key, value) -> conn.addRequestProperty(value, key));
        } else {
            //  Content-Type=application/x-www-form-urlencoded;charset=utf-8
            conn.addRequestProperty(HttpHeaders.CONTENT_TYPE, DEFAULT_CONTENT_TYPE + encoding);
            //  Accept-Charset = utf-8
            conn.addRequestProperty(HttpHeaders.ACCEPT_CHARSET, encoding);
        }
    }

    private static final char equal = '=';
    private static final char and = '&';

    /**
     * 编码
     */
    private static String encodingParams(Map<String, String> params, String encoding)
            throws UnsupportedEncodingException {
        if (null == params || params.isEmpty()) {
            return EMPTY;
        }

        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, String>> set = params.entrySet();
        for (Map.Entry<String, String> entry : set) {
            // key为空返回,直接返回
            String key = entry.getKey();
            if (key == null || key.length() == 0) {
                continue;
            }

            // key 不编码 我觉得我们不会将key设置成中文
            sb.append(key).append(equal);

            // value为空添加一个空值
            String value = entry.getValue();
            if (value == null || value.length() == 0) {
                value = EMPTY;
            }

            // url编码器 , 为啥不最后编码了, 绝对有原因的 比如 name=&age= ,结果就是 name=&age="" , 而不是 name="" , age=""
            sb.append(URLEncoder.encode(value, encoding));
            sb.append(and);
        }


        // 去除最后一个的&符号
        if (sb.length() > 0) {
            StringBuilder builder = sb.deleteCharAt(sb.length() - 1);
            sb = null;
            sb = builder;
        }

        // 获取结果
        String result = sb.toString();
        sb = null;
        return result;
    }

    /**
     * result
     */
    public final static class HttpResult {
        final public int code;
        final public String content;
        final private Map<String, String> respHeaders;

        HttpResult(int code, String content, Map<String, String> respHeaders) {
            this.code = code;
            this.content = content;
            this.respHeaders = respHeaders;
        }

        public String getHeader(String name) {
            return respHeaders.get(name);
        }
    }
}
