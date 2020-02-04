# Java的工具包

> 主要包含了java日常开发中常常遇见的工具包, 自己开发过程中有意的积累了一些

主要分为 
-  集合类 (JRE没有的集合)
-  JSON(JSON解析)
-  文件IO (读写, 文件压缩)
-  多线程 (线程池)
-  反射 (一些反射工具类)
-  网络工具包 (方便的Java Http客户端,以及URL)
-  日期操作(Local与Date)
-  序列化(多种序列化工具)
-  字符串 (分割 合并)
-  proxy 简单的代理 
-  num包 (加密算法,类型转换)
-  限流算法 (四种限流算法)





打包命令 :

```java
cd commons
mvn clean package -Dmaven.test.skip=true
```

发布命令 (-Dfile=jar包全路径/相对路径)
```java
mvn install:install-file -Dfile=commons-1.0.0.jar -DgroupId=com.common -DartifactId=java-commons -Dversion=1.0.0 -Dpackaging=jar

```
