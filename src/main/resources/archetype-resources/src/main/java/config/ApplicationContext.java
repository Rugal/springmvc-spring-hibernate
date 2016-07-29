package config;

import com.google.gson.Gson;
import com.zaxxer.hikari.HikariDataSource;
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
import rugal.sample.core.entity.Student;

/**
 * Java based application context configuration class.
 * <p>
 * Including data source and transaction manager configuration.
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

    private static final String CONNECTION_AUTOCOMMIT = "hibernate.connection.autocommit";

    private static final String FORMAT_SQL = "hibernate.format_sql";

    private static final String HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";

    private static final String SHOW_SQL = "hibernate.show_sql";

    private static final String CURRENT_SESSION_CONTEXT_CLASS = "hibernate.current_session_context_class";

    private static final String DIALECT = "hibernate.dialect";

    private static final String PACKAGE_TO_SCAN = Student.class.getPackage().getName();

    @Autowired
    private Environment env;

    @Bean
    public Gson gson()
    {
        return new Gson();
    }

//<editor-fold defaultstate="collapsed" desc="HikariCP Datasoure Configuration" >
    @Bean(destroyMethod = "close")
    @Autowired
    public DataSource dataSource()
    {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setConnectionTestQuery("SELECT 1;");
        dataSource.setMaximumPoolSize(3);
        dataSource.setAutoCommit(false);
        //------------------------------
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
        sessionFactory.setPackagesToScan(PACKAGE_TO_SCAN);
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties()
    {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(DIALECT, env.getProperty(DIALECT));
        hibernateProperties
            .put(CURRENT_SESSION_CONTEXT_CLASS, env.getProperty(CURRENT_SESSION_CONTEXT_CLASS));
        hibernateProperties.put(CONNECTION_AUTOCOMMIT, env.getProperty(CONNECTION_AUTOCOMMIT));
        hibernateProperties.put(FORMAT_SQL, env.getProperty(FORMAT_SQL));
        hibernateProperties.put(HBM2DDL_AUTO, env.getProperty(HBM2DDL_AUTO));
        hibernateProperties.put(SHOW_SQL, env.getProperty(SHOW_SQL));
//        hibernateProperties.put(hibernate_connection_provider_class, env.getProperty(hibernate_connection_provider_class));
        return hibernateProperties;

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
