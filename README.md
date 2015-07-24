springmvc-spring-hibernate archetype
==========================

An excellent skeleton code for JavaEE application based on:  

1. Springmvc 4  
2. Spring Framework 4  
3. Hibernate 4  

##Important components used

1. Connection pool: Hikari CP
2. JSON mapper: Gson

[`official page`](http://rugal.github.io/development/2014/07/06/my-archetype-in-maven/).   
This is my first contribution to maven repository, to save it save a template for future contributions.  
A great exquisit archetype, please refer to real archetype in `src/main/resources/archetype-resources` for furture inquirement.  
Usage:  

    mvn archetype:generate  -DarchetypeGroupId=ml.rugal.archetype       \
                -DarchetypeArtifactId=springmvc-spring-hibernate  \
                -DarchetypeVersion=0.5          \
                -DgroupId=your.group.id         \
                -DartifactId=your.artifact.id   \
                -Dversion=your.version