package ga.rugal.sample.core.dao.impl;

import ga.rugal.IntegrationTestBase;
import ga.rugal.sample.core.dao.CourseDAO;
import ga.rugal.sample.core.entity.Course;
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
public class CourseDAOImplIntegrationTest extends IntegrationTestBase
{

    @Autowired
    private Course course;

    @Autowired
    private CourseDAO courseDAO;

    @Before
    public void setUp()
    {
        this.courseDAO.save(this.course);
    }

    @After
    public void tearDown()
    {
        this.courseDAO.delete(this.course);
    }

    @Test
    public void get()
    {
        Course db = this.courseDAO.get(this.course.getCid());
        Assert.assertEquals(this.course, db);
    }
}
