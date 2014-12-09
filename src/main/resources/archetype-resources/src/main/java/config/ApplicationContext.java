package config;

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
 *
 * @author Rugal Bernstein
 * @since 0.2
 */
@Configuration
@EnableTransactionManagement
@PropertySource(
    {
        "classpath:jdbc.properties",
        "classpath:hibernate.properties"
    })
@ComponentScan(value = "rugal")
public class ApplicationContext
{

    public static final String hibernate_connection_autocommit = "hibernate.connection.autocommit";

    public static final String hibernate_format_sql = "hibernate.format_sql";

    public static final String hibernate_hbm2ddl_auto = "hibernate.hbm2ddl.auto";

    public static final String hibernate_show_sql = "hibernate.show_sql";

    public static final String hibernate_current_session_context_class = "hibernate.current_session_context_class";

    public static final String hibernate_dialect = "hibernate.dialect";

    public static final String package_to_scan = "rugal.sample.core.entity";

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
    @Autowired
    public LocalSessionFactoryBean sessionFactory(DataSource datasouce)
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(datasouce);
        sessionFactory.setPackagesToScan(package_to_scan);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(hibernate_dialect, env.getProperty(hibernate_dialect));
        hibernateProperties
            .put(hibernate_current_session_context_class, env.getProperty(hibernate_current_session_context_class));
        hibernateProperties.put(hibernate_connection_autocommit, env.getProperty(hibernate_connection_autocommit));
        hibernateProperties.put(hibernate_format_sql, env.getProperty(hibernate_format_sql));
        hibernateProperties.put(hibernate_hbm2ddl_auto, env.getProperty(hibernate_hbm2ddl_auto));
        hibernateProperties.put(hibernate_show_sql, env.getProperty(hibernate_show_sql));
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
