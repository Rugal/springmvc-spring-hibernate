springmvc-spring-hibernate
==========================

Now you got an excellent skeleton for JavaEE application in hand.
This skeleton based on:

1. Springmvc 4
2. Spring 4
3. Hibernate 4

###Important components used

1. Connection pool: Hikari CP
2. JSON mapper:   Gson


---------------------

Since `0.6`, I started to use Java based configuration only.
Please modify some configuration files under `config` package.

1. `ServletContainerInitializer`: Container support java only configuration since Servlet 3.0. As long as you specify this file, container will use this class as configuration file.
2. `ApplicationContext`: specify entity scanning, data source configuration and transaction.
3. `SpringMVCApplicationContext`: specify web SpringMVC related configuration such like argument resolution, message converter, view resolution etc.,
4. Some other files such like `log4j` and `jdbc` in `resources` folder.

For more details please refer to [`official page`](http://rugal.github.io/development/2014/07/06/my-archetype-in-maven/).