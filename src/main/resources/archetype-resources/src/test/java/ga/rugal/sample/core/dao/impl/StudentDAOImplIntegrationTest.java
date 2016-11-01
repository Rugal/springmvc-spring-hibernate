package ga.rugal.sample.core.dao.impl;

import ga.rugal.IntegrationTestBase;
import ga.rugal.sample.core.dao.StudentDAO;
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
public class StudentDAOImplIntegrationTest extends IntegrationTestBase
{

    @Autowired
    private Student student;

    @Autowired
    private StudentDAO studentDAO;

    @Before
    public void setUp()
    {
        this.studentDAO.save(this.student);
    }

    @After
    public void tearDown()
    {
        this.studentDAO.delete(this.student);
    }

    @Test
    public void get()
    {
        Student db = this.studentDAO.get(this.student.getSid());
        Assert.assertEquals(this.student, db);
    }
}
