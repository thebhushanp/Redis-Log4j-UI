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
  - Now run your applicaton, visit **logViewer.html**


Thats it. Have a happy logging. :)
