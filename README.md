# Redis Log4J UI
Write logs directly to Redis NOSQL database
# How to use Redis-Log4J-UI in your application - 
  - Install redis server to your system, see [how to install redis](http://redis.io/download)
  - Pull source code of **Redis-Log4J-UI**, unzip in it and then execute **ant build_jar**. These should create directory **target** where **log4j2Redis.jar** will be located. Put this jar file in your project's build path.
  - In order to view logs on UI, you have to add following code in **web.xml** of your project
```xml
<servlet>
  <description></description>
  <display-name>LogPuller</display-name>
  <servlet-name>LogPuller</servlet-name>
  <servlet-class>the.bhushan.serlvet.LogPuller</servlet-class>
</servlet>
<servlet-mapping>
  <servlet-name>LogPuller</servlet-name>
  <url-pattern>/LogPuller</url-pattern>
</servlet-mapping>
```
**log4j.properties configuration**
  - log4j.appender.redisAppender=the.bhushan.log.appenders.RedisAppender
  - log4j.appender.redisAppender.layout=net.logstash.log4j.JSONEventLayoutV1
  - log4j.appender.redisAppender.redisPort=6379
  - log4j.appender.redisAppender.redisHost=localhost
  - log4j.appender.redisAppender.key=bhushan

**Dependency required for Redis-Log4j-UI**
  - commons-lang-2.6.jar
  - gson-2.2.4.jar
  - jedis-2.4.2.jar
  - json-smart-1.1.1.jar
  - log4j-1.2.17.jar

- Now run your applicaton, visit **logViewer.html**

Thats it. Have a happy logging. :)
