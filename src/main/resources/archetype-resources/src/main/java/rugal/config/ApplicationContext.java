/*
 * Copyright 2014 e563642.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rugal.config;

import com.jolbox.bonecp.BoneCPDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Java based application context configuration class.
 * @author Rugal Bernstein
 * @since 0.2
 */
@Configuration
@EnableTransactionManagement
@PropertySource(
    {
        "classpath:jdbc.properties"
    })
@ComponentScan(value = "rugal")
public class ApplicationContext
{

    @Autowired
    private Environment env;

//<editor-fold defaultstate="collapsed" desc="Hikari connection pool configure">
//    @Bean
//    public HikariConfig hikariConfig()
//    {
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
//        hikariConfig.setUsername(env.getProperty("jdbc.username"));
//        hikariConfig.setPassword(env.getProperty("jdbc.password"));
//        hikariConfig.setMaximumPoolSize(3);
//        hikariConfig.setJdbc4ConnectionTest(false);
//        hikariConfig.setConnectionTestQuery("SELECT 1;");
//        Properties dataSourceProperties = new Properties();
//        dataSourceProperties.put("serverName", "localhost");
//        dataSourceProperties.put("databaseName", "postgres");
//        hikariConfig.setDataSourceProperties(dataSourceProperties);
//        return hikariConfig;
//    }
//
//    @Bean(destroyMethod = "shutdown")
//    public DataSource dataSource()
//    {
//        HikariDataSource dataSource = new HikariDataSource(hikariConfig());
//        return dataSource;
//    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="BoneCP configuration">
    @Bean(destroyMethod = "close")
    public DataSource dataSource()
    {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setIdleConnectionTestPeriodInMinutes(60);
        dataSource.setIdleMaxAgeInMinutes(240);
        dataSource.setMaxConnectionsPerPartition(10);
        dataSource.setMinConnectionsPerPartition(1);
        dataSource.setPartitionCount(1);
        dataSource.setAcquireIncrement(5);
        dataSource.setStatementsCacheSize(100);
        return dataSource;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Hibernate Session factory configuration">
    @Bean
    public LocalSessionFactoryBean sessionFactory()
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("rugal.sample.core.entity");
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties
            .put("hibernate.current_session_context_class", "org.springframework.orm.hibernate4.SpringSessionContext");
        hibernateProperties.put("hibernate.connection.autocommit", "false");
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "validate");
        hibernateProperties.put("hibernate.show_sql", "true");
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Hibernate transaction manager">
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
    {
        HibernateTransactionManager txManager = new HibernateTransactionManager(sessionFactory);
        return txManager;
    }
//</editor-fold>
}
