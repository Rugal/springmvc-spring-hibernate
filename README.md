springmvc-spring-hibernate archetype
==========================

An excellent skeleton code for JavaEE application based on:

1. Springmvc 4
2. Spring Framework 4
3. Hibernate 4

###Important components used

1. Connection pool: Hikari CP
2. JSON mapper: Gson


---------------------------

This is my first contribution to maven repository, to save it save a template for future contributions.
Usage:

    mvn archetype:generate  -DarchetypeGroupId=ml.rugal.archetype       \
                -DarchetypeArtifactId=springmvc-spring-hibernate  \
                -DarchetypeVersion=0.5          \
                -DgroupId=your.group.id         \
                -DartifactId=your.artifact.id   \
                -Dversion=your.version


For more details please refer to[`official page`](http://rugal.github.io/development/2014/07/06/my-archetype-in-maven/).


--------------------------------

#version log

###0.6
Replace all XML file with Java based configuration. Removed jackson products.  

###0.5
I start to use [HikariCP](https://github.com/brettwooldridge/HikariCP) as connection pool. Replace Jackson with Gson.

###0.3
I extract basic Hibernate util classes into [ssh-common](https://github.com/Rugal/ssh-common).

###0.2
I start to use Java based configuration for application context and web context.



#commit log

###2015-07-25
Remove all jackson products.  

###2015-07-24 15:14
Remove all xml configuration file. Use annotation based configuration only.
Remove embeded tomcat plugin.
Use new embeded Jetty plugin to replace the old one.
Clean Pom file a little bit.

###2015-02-17
Deprecate Jackson data binding, use Gson instead.
