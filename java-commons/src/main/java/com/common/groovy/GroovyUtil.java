package com.common.groovy;

import groovy.lang.GroovyClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * todo
 *
 * @date: 2020-04-26 23:02
 * @author：<a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class GroovyUtil {

    /**
     * groovy class loader
     */
    private GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
    private ConcurrentMap<String, Class<?>> CLASS_CACHE = new ConcurrentHashMap<>();

    private Class<?> getCodeSourceClass(String codeSource) {
        try {
            // md5
            byte[] md5 = MessageDigest.getInstance("MD5").digest(codeSource.getBytes());
            String md5Str = new BigInteger(1, md5).toString(16);

            Class<?> clazz = CLASS_CACHE.get(md5Str);
            if (clazz == null) {
                clazz = groovyClassLoader.parseClass(codeSource);
                CLASS_CACHE.putIfAbsent(md5Str, clazz);
            }
            return clazz;
        } catch (Exception e) {
            return groovyClassLoader.parseClass(codeSource);
        }
    }


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        GroovyUtil groovyUtil = new GroovyUtil();

        Class<?> sourceClass = groovyUtil.getCodeSourceClass("package com.common.reflect;\n" +
                "\n" +
                "/**\n" +
                " * todo\n" +
                " *\n" +
                " * @date: 2020-04-26 23:06\n" +
                " * @author：<a href='mailto:fanhaodong516@qq.com'>Anthony</a>\n" +
                " */\n" +
                "public class Demo implements Run{\n" +
                "\n" +
                "    @Override\n" +
                "    public void invoke() {\n" +
                "        System.out.println(\"invoke\");\n" +
                "    }\n" +
                "}");

        Run o = (Run) sourceClass.getConstructor().newInstance();

        o.invoke();

        System.out.println(o);
    }

}
