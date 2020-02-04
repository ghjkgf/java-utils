package com.common.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class HttpClientTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(50);

        IntStream.range(0,50).forEach(e-> service.execute(()->{
            HttpClient.HttpResult httpResult = HttpClient.httpGet("http://localhost:8080/user/get");
            System.out.println(httpResult.content);
        }));

        service.shutdown();
        service.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("END");
    }

    @Test
    public void testUser(){



    }


    @Test
    public void test() {
        HttpClient.HttpResult httpResult = HttpClient.httpGet("http://127.0.0.1:9090");

        String content = httpResult.content;

        JSONObject jsonObject = JSON.parseObject(content);


        Object message = jsonObject.get("message");


        System.out.println(message);

        System.out.println("httpResult.content = " + httpResult.content);

        boolean ipv4 = IPUtil.isIPV6("0000:0000:0000:0000:0000:0000:0000:0001");
        System.out.println(ipv4);

        System.out.println("HttpClient.getPrefix() = " + HttpClient.getPrefix());
    }


    /**
     * http://localhost:8080/get?name=xiaoli&age=1
     */
    @Test
    public void testGet() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "");
        map.put("age","");
        HttpClient.HttpResult result = HttpClient.httpGet("http://localhost:8888/get", map);
        System.out.println("result.content = " + result.content);
        System.out.println("result.code = " + result.code);

        String header = result.getHeader("Access-Control-Allow-Origin");

        System.out.println(header);

        String head = result.getHeader("Content-Type");

        System.out.println(head);

    }

    /**
     * http://localhost:8080/get/xiaoli/age=1
     */
    @Test
    public void testGet2() {
        HttpClient.HttpResult result = HttpClient.httpGet("http://localhost:8080/get/name/1");
        System.out.println("result.content = " + result.content);
    }

    /**
     * post
     * http://localhost:8080/post
     */
    @Test
    public void testPost3() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "小李");
        map.put("age", 1 + "");
        HttpClient.HttpResult result = HttpClient.httpPost("http://localhost:8080/post", map);
        System.out.println("result.content = " + result.content);
    }

    /**
     * put
     * http://localhost:8080/put
     */
    @Test
    public void testPut() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "小李");
        map.put("age", 1 + "");
        HttpClient.HttpResult result = HttpClient.httpPut("http://localhost:8080/put", map);
        System.out.println("result.content = " + result.content);
        System.out.println("result.code = " + result.code);
    }

    @Test
    public void testecho() throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("", HttpClient.UTF_8);
        System.out.println(encode);
    }

    public List<String> builderHeaders() {
        List<String> headers = Arrays.asList(
                "Client-Version", "1",
                "User-Agent", "1",
                "Accept-Encoding", "gzip,deflate,sdch",
                "Connection", "Keep-Alive",
                "RequestId", "1",
                "Request-Module", "Naming");
        return headers;
    }

}