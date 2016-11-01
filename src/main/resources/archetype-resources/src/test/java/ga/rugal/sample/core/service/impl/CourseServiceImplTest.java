package ga.rugal.sample.core.service.impl;

import ga.rugal.UnitTestBase;
import ga.rugal.sample.core.dao.CourseDAO;
import ga.rugal.sample.core.entity.Course;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Rugal Bernstein
 */
public class CourseServiceImplTest extends UnitTestBase
{

    private CourseDAO courseDAO = Mockito.mock(CourseDAO.class, (Answer) (invocation) -> null);

    @Autowired
    private Course course;

    @Autowired
    private CourseServiceImpl courseService;

    @Before
    public void before()
    {
        this.courseService.setCourseDAO(this.courseDAO);
    }

    @Test
    public void update()
    {
        this.courseService.update(this.course);
    }
}
