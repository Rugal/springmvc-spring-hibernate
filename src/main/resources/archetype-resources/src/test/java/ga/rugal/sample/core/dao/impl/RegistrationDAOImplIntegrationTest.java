package ga.rugal.sample.core.dao.impl;

import ga.rugal.IntegrationTestBase;
import ga.rugal.sample.core.dao.CourseDAO;
import ga.rugal.sample.core.dao.RegistrationDAO;
import ga.rugal.sample.core.dao.StudentDAO;
import ga.rugal.sample.core.entity.Course;
import ga.rugal.sample.core.entity.Registration;
import ga.rugal.sample.core.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Rugal Bernstein
 */
@Slf4j
public class RegistrationDAOImplIntegrationTest extends IntegrationTestBase
{

    @Autowired
    private Registration registration;

    @Autowired
    private Course course;

    @Autowired
    private Student student;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private RegistrationDAO registrationDAO;

    @Before
    public void setUp()
    {
        this.studentDAO.save(this.student);
        this.courseDAO.save(this.course);
        this.registrationDAO.save(this.registration);
    }

    @After
    public void tearDown()
    {
        this.registrationDAO.delete(this.registration);
        this.courseDAO.delete(this.course);
        this.studentDAO.delete(this.student);
    }

    @Test
    public void get()
    {
        Registration db = this.registrationDAO.get(this.registration.getRid());
        Assert.assertEquals(this.registration, db);
    }
}
