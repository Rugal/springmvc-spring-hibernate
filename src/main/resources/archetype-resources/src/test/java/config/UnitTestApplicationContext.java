package config;

import ga.PackageInfo;
import org.hibernate.SessionFactory;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Rugal Bernstein
 */
@ComponentScan(basePackageClasses = PackageInfo.class)
@Configuration
public class UnitTestApplicationContext
{

    @Bean
    public SessionFactory sessionFactory()
    {
        SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
        return sessionFactory;
    }
}
