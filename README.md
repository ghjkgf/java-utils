# Java的工具包

## 本框架介绍

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

## 推荐工具类

> ​	这里的版本可以去 [https://mvnrepository.com/](https://mvnrepository.com/)  自己找, 我的版本不一定是你需要的 . 

### 序列化工具

> ​	这个在自己做项目 , 经常会遇到选型. 

#### Protobuf

伟大的protobuf 提供了 高性能的序列化.  我们可以直接使用  

链接 : [https://developers.google.com/protocol-buffers/](https://developers.google.com/protocol-buffers/)

```java
<dependency>
  <groupId>com.google.protobuf</groupId>
  <artifactId>protobuf-java</artifactId>
  <version>3.11.0</version>
</dependency>
```

#### FastJSON

> ​	BUG 最多的. 项目之一. 

链接 :  https://github.com/alibaba/fastjson

```xml
<!--序列化 fastjson,性能高,虽然有一些问题-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.58</version>
</dependency>
```

#### MessagePack

https://msgpack.org/

```java
<!-- MessagePack 使用起来很方便 -->
<dependency>
    <groupId>org.msgpack</groupId>
    <artifactId>msgpack</artifactId>
    <version>0.6.12</version>
</dependency>
```

####  Hessian2

```xml
<!-- https://mvnrepository.com/artifact/org.apache.dubbo/dubbo-serialization-hessian2 -->
<dependency>
    <groupId>org.apache.dubbo</groupId>
    <artifactId>dubbo-serialization-hessian2</artifactId>
    <version>2.7.1</version>
    <scope>test</scope>
</dependency>
```

####  Jackson

> ​	公认的Java最好的序列化JSON工具 , 但是效率没有FastJSON快.

```xml
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.10.1</version>
</dependency>
```

####  commons-codec

> ​	跟commons-lang3 一样, 基本公认的最好用的工具包.

```xml
 <dependency>
     <groupId>commons-codec</groupId>
     <artifactId>commons-codec</artifactId>
     <version>1.12</version>
 </dependency>
```



### 通用工具框架

#### commons-long3 

> ​	很多的工具类基本都涵盖着

```java
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.9</version>
</dependency>
```

#### guava 框架

> ​	这个是google的框架 , 确实很棒.   链接 : [https://github.com/google/guava](https://github.com/google/guava)  , jre后缀代表是Java, 还有Android版本.

```xml
<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
    <version>28.0-jre</version>
</dependency>
```

#### Lombox 框架

> ​	这个最为熟悉了, 一个字节码生成技术.  可以帮助你在编译的时候生成大量重复的代码 , 链接 :  [https://projectlombok.org/](https://projectlombok.org/)

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.10</version>
</dependency>
```

### 字节码技术

#### ASM 

> ​	核心技术, 一般人不会用

#### CGLIB

> ​	基本Java继承实现的一种代理关系. Final类无缘.

```xml
<!-- https://mvnrepository.com/artifact/cglib/cglib -->
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>3.2.5</version>
</dependency>
```

#### javassist

> ​	这个我觉得比较简单上手 ,  如果真正是自己操作字节码的话. 

```java
<dependency>
    <groupId>org.javassist</groupId>
    <artifactId>javassist</artifactId>
    <version>3.20.0-GA</version>
</dependency
```

### 文件操作工具

#### Snappy 

> ​	文件压缩工具包 ,压缩算法学一波 ..哈哈哈哈

```xml
<dependency>
    <groupId>org.xerial.snappy</groupId>
    <artifactId>snappy-java</artifactId>
    <version>1.1.7.2</version>
</dependency>
```

#### thumbnailator

> ​	好像还行, 其实不行就用 OpenCv了, 也有Java的包, 处理图形的. 

```xml
<dependency>
    <groupId>net.coobird</groupId>
    <artifactId>thumbnailator</artifactId>
    <version>0.4.8</version>
</dependency>
```



### 日志

#### SLFG

Slfg 是接口抽象(可以说是规范), 其他都是他的实现

```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.21</version>
</dependency>
```

比如log4j . 版本号记得最好一致和slfg的.

```xml
<dependency>
<groupId>org.slf4j</groupId>
<artifactId>slf4j-log4j12</artifactId>
<version>1.7.21</version>
</dependency>
```

附赠一个使用多年的 `log4j.properties` 文件

```properties
# 第一个参数可以调成: TRACE  DEBUG  INFO  WARN ERROR 之类的 , 测试环境下用DEBUG足矣
log4j.rootCategory=DEBUG,CONSOLE,LOGFILE
log4j.logger.org.apache.axis.enterprise=FATAL,CONSOLE
log4j.appender.CONSOLE=org.apache.log4j.ConsoleAppender
log4j.appender.CONSOLE.layout=org.apache.log4j.PatternLayout
log4j.appender.CONSOLE.layout.ConversionPattern=%d{ISO8601} %-6r [%6.6t] %-5p %30.30c %x - %m\n
##输出到文件(这里默认为追加方式)
log4j.appender.LOGFILE=org.apache.log4j.FileAppender
##设置文件输出路径
##【1】文本文件
log4j.appender.LOGFILE.File=${user.dir}/server.log
log4j.appender.LOGFILE.Append=true
##设置文件输出样式
log4j.appender.LOGFILE.layout=org.apache.log4j.PatternLayout
log4j.appender.LOGFILE.layout.ConversionPattern=%d{ISO8601} %-6r [%6.6t] %-5p %30.30c %x - %m\n
```

#### Struts-Log4j

> ​	可以看看我这篇文章 : [https://anthony-dong.github.io/post/YVlL69JfN/]( https://anthony-dong.github.io/post/YVlL69JfN/)  , 介绍了如何使用. 

```xml
<dependency>
    <groupId>structlog4j</groupId>
    <artifactId>structlog4j-api</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 测试

#### junit 

> ​	么的说 , 反正我只用过这个

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
</dependency>
```



####  MockITO 

> ​	这个解决了依赖的问题. 很好地做了隔离性. 

```xml
<!-- https://mvnrepository.com/artifact/org.mockito/mockito-core -->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>3.0.0</version>
    <scope>test</scope>
</dependency>
```



### Swagger-UI

我觉得是Spring项目中 , 必不可少的东西. 在测试环境下的. 

依赖 :  我是用的是V2版本 . 

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
```

JavaCode, 告诉你如何加入依赖. 

```java
package com.example.springswagger.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger
 *
 * @date:2020/4/3 14:37
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 访问接口在 "com.example.springswagger.controller" 中
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springswagger.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiEndPointsInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("REST API")
                .description("Spring Swaager2 REST API")
                .contact(new Contact("anthony", "https://github.com/Anthony-Dong", "574986060@qq.com"))
                .license("The Apache License")
                .licenseUrl("https://opensource.org/licenses/MIT")
                .version("V1")
                .build();
    }
}
```



### 协程框架

> ​	Fiber 是一种轻量级的线程, 可以说是一种基于线程上实现的一种线程, 和Goroutine一样. Java的线程模型基本上是基于原生的线程的 , 调度耗时 . 

#### Quasar

java的一种框架

```xml
<dependency>
    <groupId>co.paralleluniverse</groupId>
    <artifactId>quasar-core</artifactId>
    <version>0.7.9</version>
    <classifier>jdk8</classifier>
</dependency>
```

#### Kotlin

> 他也是一种实现



未来的基于 forkjoin框架的那个 线程池实现协程调度. 