springmvc-spring-hibernate
==========================

##(update log)[http://rugal.github.io/development/2014/07/06/my-archetype-in-maven/]  

This is my first contribution to maven repository, to save it save a template for future contributions.  

A great exquisit archetype, please refer to real archetype in `src/main/resources/archetype-resources` for furture inquirement.  

    mvn archetype:generate  -DarchetypeGroupId=ml.rugal.archetype       \
                -DarchetypeArtifactId=springmvc-spring-hibernate  \
                -DarchetypeVersion=0.2          \
                -DgroupId=your.group.id         \
                -DartifactId=your.artifact.id   \
                -Dversion=your.version


Since version `0.2`, we start to use Java based configuration for application context and web context.
