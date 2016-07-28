package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rugal.sample.core.entity.Student;

/**
 * Java based application context configuration class.
 * <p>
 * Including data source and transaction manager configuration.
 *
 * @author Rugal Bernstein
 * @since 0.7
 */
@Configuration
public class TestApplicationContext
{

    @Bean
    public Student student()
    {
        final Student s = new Student();
        s.setId(0);
        s.setAge(22);
        s.setName("Rugal");
        return s;
    }
}
