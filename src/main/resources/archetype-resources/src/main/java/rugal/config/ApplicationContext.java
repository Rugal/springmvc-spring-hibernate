/*
 * Copyright 2014 Rugal Bernstein.
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

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:jdbc.properties"})
@ComponentScan(value = "rugal")
public class ApplicationContext {

    @Autowired
    private Environment env;

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        hikariConfig.setUsername(env.getProperty("jdbc.username"));
        hikariConfig.setPassword(env.getProperty("jdbc.password"));
        hikariConfig.setMaximumPoolSize(3);
        hikariConfig.setJdbc4ConnectionTest(false);
        hikariConfig.setConnectionTestQuery("SELECT 1;");
        Properties dataSourceProperties = new Properties();
        dataSourceProperties.put("serverName", "localhost");
        dataSourceProperties.put("databaseName", "postgres");
        hikariConfig.setDataSourceProperties(dataSourceProperties);
        return hikariConfig;
    }

    @Bean(destroyMethod = "shutdown")
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource(hikariConfig());
        return dataSource;
    }

    @Bean
    public HttpMessageConverter messageConverters() {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.valueOf("text/javascript"));
        messageConverter.setSupportedMediaTypes(supportedMediaTypes);
        return messageConverter;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("rugal.sample.core.entity");
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate4.SpringSessionContext");
        hibernateProperties.put("hibernate.connection.autocommit", "false");
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "validate");
        hibernateProperties.put("hibernate.show_sql", "true");
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager(sessionFactory);
        return txManager;
    }
}
