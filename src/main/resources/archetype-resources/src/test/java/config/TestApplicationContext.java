package config;

import com.google.gson.Gson;
import ga.rugal.sample.core.entity.Course;
import ga.rugal.sample.core.entity.Registration;
import ga.rugal.sample.core.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Rugal Bernstein
 */
@Configuration
public class TestApplicationContext
{
    @Bean
    public Gson gson()
    {
        return new Gson();
    }

    @Bean
    @Scope("prototype")
    public Student student()
    {
        Student student = new Student();
        student.setName("TEST");
        student.setSid(100);
        return student;
    }

    @Bean
    @Scope("prototype")
    public Course course()
    {
        Course course = new Course();
        course.setCid(100);
        course.setName("TEST");
        return course;
    }

    @Bean
    @Scope("prototype")
    public Registration registration(Student student, Course course)
    {
        Registration registration = new Registration();
        registration.setRid(100);
        registration.setCourse(course);
        registration.setStudent(student);
        return registration;
    }
}
