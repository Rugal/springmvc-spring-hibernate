package config;

import com.google.gson.Gson;
import com.zaxxer.hikari.HikariDataSource;
import ga.rugal.sample.core.entity.PackageInfo;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Java based application context configuration class.
 * <p>
 * Including data source and transaction manager configuration.
 *
 * @author Rugal Bernstein
 * @since 0.2
 */
@ComponentScan(basePackageClasses = ga.PackageInfo.class)
@Configuration
@EnableTransactionManagement
@PropertySource(
    {
        "classpath:hibernate.properties", "classpath:jdbc_${spring.profiles.active}.properties"
    })
public class ApplicationContext
{

    public static final String AUTOCOMMIT = "hibernate.connection.autocommit";

    public static final String FORMAT_SQL = "hibernate.format_sql";

    public static final String AUTO_DDL = "hibernate.hbm2ddl.auto";

    public static final String SHOW_SQL = "hibernate.show_sql";

    public static final String CONTEXT_CLASS = "hibernate.current_session_context_class";

    public static final String DIALECT = "hibernate.dialect";

    public static final String PACKAGE_TO_SCAN = PackageInfo.class.getPackage().getName();

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${" + AUTOCOMMIT + "}")
    private String autocommit;

    @Value("${" + FORMAT_SQL + "}")
    private String formatSql;

    @Value("${" + AUTO_DDL + "}")
    private String autoDDL;

    @Value("${" + SHOW_SQL + "}")
    private String showSql;

    @Value("${" + CONTEXT_CLASS + "}")
    private String contextClass;

    @Value("${" + DIALECT + "}")
    private String dialect;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Bean
    public Gson GSON()
    {
        return new Gson();
    }

//<editor-fold defaultstate="collapsed" desc="HikariCP Data Source Configuration">
    @Bean(destroyMethod = "close")
    public DataSource dataSource()
    {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        dataSource.setJdbcUrl(this.url);
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setConnectionTestQuery("SELECT 1;");
        dataSource.setMaximumPoolSize(3);
        dataSource.setAutoCommit(false);
        return dataSource;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Hibernate Session factory configuration">
    @Bean
    @Autowired
    public LocalSessionFactoryBean sessionFactory(DataSource datasouce, Properties hibernateProperties)
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(datasouce);
        sessionFactory.setPackagesToScan(PACKAGE_TO_SCAN);
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    public Properties hibernateProperties()
    {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(DIALECT, dialect);
        hibernateProperties.put(CONTEXT_CLASS, contextClass);
        hibernateProperties.put(AUTOCOMMIT, autocommit);
        hibernateProperties.put(FORMAT_SQL, formatSql);
        hibernateProperties.put(AUTO_DDL, autoDDL);
        hibernateProperties.put(SHOW_SQL, showSql);
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
