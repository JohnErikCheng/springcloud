##### SpringCloud客户端注册到eureka
######一、描述
```text
1、eureka是springcloud的服务注册中心
2、客户端可以注册到服务注册中心
```
######二、注册流程
######1、pom文件配置
```xml
<!-- eureka依赖 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
    <version>1.4.6.RELEASE</version>
</dependency>

```
######2、yml文件配置
```yml
server:
  port: 9001
  
spring:
  application:
    name: springcloud-eureka-client # 指定微服务的名称后续在调用的时候只需要使用该名称就可以进行服务的访问，在eureka客户端UI中也宣誓这个名称
    
eureka:
  client:
    service-url:
      defaultZone: http://localhost:9999/eureka/
      
logging:
  pattern:
    console: %d{yyyy/MM/dd-HH:mm:ss} [%thread] %-5level %logger- %msg%n #控制态输出样式的配置
    file: %d{yyyy/MM/dd-HH:mm} [%thread] %-5level %logger- %msg%n  #单独文件输出格式配置
  path: D:/Workpaces/IdeaWorkspace/springcloud/  #单独文件输出位置
  file: logging.log                              #单独文件输出位置+文件名
  level: trace                                   #日志输出的级别
```
######3、主配置类
> 主配置类上添加 @EnableDiscoveryClient注解，表示该服务可注册到eureka上，能被eureka发现

-----------------------------------
_**日志问题调研**_
#### 1、日志框架

|             日志门面              |          日志实现           |
| :-------------------------------: | :-------------------------: |
| ~~JCL~~、SLF4J、~~jboss-logging~~ | Log4j、JUL、Log4j2、Logback |

日志门面：SLF4J
日志实现：Logback
SpringBoot：底层是Spring框架，Spring框架默认是用JCL；

##### 	SpringBoot选用SLF4J和Logback.

#### 2、SLF4J使用

以后开发的时候，日志记录。日志记录方法的调用，不应直接调用日志的实现类，而是调用日志抽象层的方法。

应该给系统里倒入slf4j的jar和logback的实现jar。

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    logger.info("Hello World");
  }
}
```

![click to enlarge](https://www.slf4j.org/images/concrete-bindings.png)

每一个日志的实现框架都有自己的配置文件。使用slf4j以后，**配置文件还是做成日志实现框自己本身的配置文件**。

#### 3、遗留问题

框架A:

​    (slf4j+logback):Spring（commons-logging）、Hibernate(jboss-logging)、Mybatis、xxx

统一：统一使用slf4j+logback

![click to enlarge](https://www.slf4j.org/images/legacy.png)

**如何让系统中的日志统一到slf4j：**
==1、将系统中的其它日志框架先排除出去==
==2、用中间包来替换所有的日志框架==
==3、我们倒入slf4j的其它实现==

**日志级别：**

```properties
trace > debug > info > warn > error
```

**The following example shows potential(默认的、潜在的) logging settings in application.properties:**

```properties
logging.level.root=WARN
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate=ERROR
```

**日志在yml中的相关配置：**
```properties
logging.level.com.itcast=info
logging.path=日志保存的位置
logging.file=D:/logging.log 日志的文件包含路径
logging.pattern.console=console: %d{yyyy/MM/dd-HH:mm:ss} [%thread] %-5level %logger- %msg%n 控制台输出日志的格式
logging.pattern.file: %d{yyyy/MM/dd-HH:mm} [%thread] %-5level %logger- %msg%n 在指定文件中日志输出格式
```

| `logging.file` | `logging.path`     | Example    | Description                                                  |
| -------------- | ------------------ | ---------- | ------------------------------------------------------------ |
| *(none)*       | *(none)*           |            | Console only logging.                                        |
| Specific file  | *(none)*           | `my.log`   | Writes to the specified log file. Names can be an exact location or relative to the current directory. |
| *(none)*       | Specific directory | `/var/log` | Writes `spring.log` to the specified directory. Names can be an exact location or relative to the current directory. |

**Depending on your logging system, the following files are loaded:**

>  ==使用springboot加载不同方式的日志实现方式，需要使用的配置文件名称如下，存放位置在项目根目录下==，==举例logback.xml==

| Logging System          | Customization                                                |
| ----------------------- | ------------------------------------------------------------ |
| Logback                 | `logback-spring.xml`, `logback-spring.groovy`, `logback.xml`, or `logback.groovy` |
| Log4j2                  | `log4j2-spring.xml` or `log4j2.xml`                          |
| JDK (Java Util Logging) | `logging.properties`                                         |

**logback.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--
scan：当此属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true。
scanPeriod：设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒当scan为true时，此属性生效。默认的时间间隔为1分钟。
debug：当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。
-->
<configuration scan="false" scanPeriod="60 seconds" debug="false">
    <!-- 定义日志的根目录 -->
    <property name="LOG_HOME" value="/app/log" />
    <!-- 定义日志文件名称 -->
    <property name="appName" value="atguigu-springboot"></property>
    <!-- ch.qos.logback.core.ConsoleAppender 表示控制台输出 -->
    <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <!--
        日志输出格式：
			%d表示日期时间，
			%thread表示线程名，
			%-5level：级别从左显示5个字符宽度
			%logger{50} 表示logger名字最长50个字符，否则按照句点分割。 
			%msg：日志消息，
			%n是换行符
        -->
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </layout>
    </appender>

    <!-- 滚动记录文件，先将日志记录到指定文件，当符合某个条件时，将日志记录到其他文件 -->  
    <appender name="appLogAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- 指定日志文件的名称 -->
        <file>${LOG_HOME}/${appName}.log</file>
        <!--
        当发生滚动时，决定 RollingFileAppender 的行为，涉及文件移动和重命名
        TimeBasedRollingPolicy： 最常用的滚动策略，它根据时间来制定滚动策略，既负责滚动也负责出发滚动。
        -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--
            滚动时产生的文件的存放位置及文件名称 %d{yyyy-MM-dd}：按天进行日志滚动 
            %i：当文件大小超过maxFileSize时，按照i进行文件滚动
            -->
            <fileNamePattern>${LOG_HOME}/${appName}-%d{yyyy-MM-dd}-%i.log</fileNamePattern>
            <!-- 
            可选节点，控制保留的归档文件的最大数量，超出数量就删除旧文件。假设设置每天滚动，
            且maxHistory是365，则只保存最近365天的文件，删除之前的旧文件。注意，删除旧文件是，
            那些为了归档而创建的目录也会被删除。
            -->
            <MaxHistory>365</MaxHistory>
            <!-- 
            当日志文件超过maxFileSize指定的大小是，根据上面提到的%i进行日志文件滚动 注意此处配置SizeBasedTriggeringPolicy是无法实现按文件大小进行滚动的，必须配置timeBasedFileNamingAndTriggeringPolicy
            -->
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>100MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <!-- 日志输出格式： -->     
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [ %thread ] - [ %-5level ] [ %logger{50} : %line ] - %msg%n</pattern>
        </layout>
    </appender>

    <!-- 
		logger主要用于存放日志对象，也可以定义日志类型、级别
		name：表示匹配的logger类型前缀，也就是包的前半部分
		level：要记录的日志级别，包括 TRACE < DEBUG < INFO < WARN < ERROR
		additivity：作用在于children-logger是否使用 rootLogger配置的appender进行输出，
		false：表示只用当前logger的appender-ref，true：
		表示当前logger的appender-ref和rootLogger的appender-ref都有效
    -->
    <!-- hibernate logger -->
    <logger name="com.atguigu" level="debug" />
    <!-- Spring framework logger -->
    <logger name="org.springframework" level="debug" additivity="false"></logger>

    <!-- 
    root与logger是父子关系，没有特别定义则默认为root，任何一个类只会和一个logger对应，
    要么是定义的logger，要么是root，判断的关键在于找到这个logger，然后判断这个logger的appender和level。 
    -->
    <root level="info">
        <appender-ref ref="stdout" />
        <appender-ref ref="appLogAppender" />
    </root>
</configuration> 
```

**You need to either use `logback-spring.xml` or define a `logging.config` property.**

使用logging-spring.xml这种方式,可以根据不同配置环境配置日志的输出方式。

```xml
<springProfile name="staging">
    <!-- configuration to be enabled when the "staging" profile is active -->
</springProfile>

<springProfile name="dev, staging">
    <!-- configuration to be enabled when the "dev" or "staging" profiles are active -->
</springProfile>

<springProfile name="!production">
    <!-- configuration to be enabled when the "production" profile is not active -->
</springProfile>
```

**案例：**
```xml
<layout class="ch.qos.logback.classic.PatternLayout">
   <springProfile name="dev">
        <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
	</springProfile>
</layout>
```
相关技术文档网页支持：
```html
1、https://docs.spring.io/spring-boot/docs/1.5.20.RELEASE/reference/htmlsingle/#howto-logging
2、https://www.slf4j.org/manual.html
3、https://www.slf4j.org/legacy.html
```

