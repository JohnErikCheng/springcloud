##### Ribbon
###### 1、Ribbon的描述
```text
1、Ribbon是一个基于HTTP和TCP客户端负载均衡器。Feign中也使用Ribbon。
2、Ribbon可以在通过客户端中配置的ribbonServerList服务列表去轮询访问以达到负载均衡的作用。
3、当Ribbon与Eureka联合使用的时候，ribbonServerList会被DiscoveryEnableNIWSServerList重写，
扩展成从Eureka注册中心中获取服务器列表。同时它也会用NIWSDiscoveryPing来取代IPing,它的职责委托给Eureka来确定服务端是否已经启动。
```
###### 2、Ribbon的环境搭建
1、启动Eureka服务
2、启动生产者eureka-client服务
3、
