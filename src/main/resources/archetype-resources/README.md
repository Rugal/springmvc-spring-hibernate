springmvc-spring-hibernate
==========================

Now you got an excellent skeleton for JavaEE application in hand.  
This skeleton based on:  

1. Springmvc
2. Spring
3. Hibernate


##Important components used

1. Connection pool: Hikari CP
2. JSON mapper:   Gson

Since `0.6`, I started to use Java based configuration only.  
Please manually modify some configuration files under `conf` package.  

1. `ServletContainerInitializer`: Container support java only configuration since Servlet 3.0. As long as you specify this file, container will use this class as configuration file.  
2. `ApplicationContext`: specify entity scanning, data source configuration and transaction.  
3. `SpringMVCApplicationContext`: specify web SpringMVC related configuration such like argument resolution, message converter, view resolution etc.,  
4. Some other files such like `log4j` and `jdbc` in `resources` folder.   

[`official page`](http://rugal.github.io/development/2014/07/06/my-archetype-in-maven/).   


#version log
Since version `0.6`, Replace all XML file with Java based configuration.   
Since version `0.5`, I start to use [HikariCP](https://github.com/brettwooldridge/HikariCP) as connection pool. Replace Jackson with Gson.  
Since version `0.3`, I extract basic Hibernate util classes into [ssh-common](https://github.com/Rugal/ssh-common).  
Since version `0.2`, I start to use Java based configuration for application context and web context.  


#commit log

###2015-07-24 15:14
Remove all xml configuration file. Use annotation based configuration only.  
Remove embeded tomcat plugin.  
Use new embeded Jetty plugin to replace the old one.  
Clean Pom file a little bit.  

###2015-02-17
Deprecate Jackson data binding, use Gson instead.  