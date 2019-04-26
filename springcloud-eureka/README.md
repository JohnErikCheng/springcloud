##### SpringCloud之Eureka搭建
######一、创建eureka服务
######二、添加pom依赖
```xml
<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-starter-eureka-server</artifactId>
   <version>1.4.6.RELEASE</version>
</dependency>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Greenwich.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```
######三、yml文件
```yaml
server:
  port: 9999
eureka:
  # 服务的事例主机名
  instance:
    hostname: register-master
  client:
    # 通过注册register-with-eureka、fetch-registry表示自己是一个eureka服务
    register-with-eureka: false # 检索服务选项，当设置为True(默认值)时，会进行服务检索,注册中心不负责检索服务。
    fetch-registry: false # 服务注册中心也会将自己作为客户端来尝试注册自己,为true（默认）时自动生效
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
spring:
  application:
    name: springcloud-eureka
```
######四、启动类上添加注解
>**@EnableEurekaServer**
#####五、访问
> http://ip:port
