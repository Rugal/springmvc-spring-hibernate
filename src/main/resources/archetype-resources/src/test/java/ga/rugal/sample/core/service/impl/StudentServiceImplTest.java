package ga.rugal.sample.core.service.impl;

import ga.rugal.UnitTestBase;
import ga.rugal.sample.core.dao.StudentDAO;
import ga.rugal.sample.core.entity.Student;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Rugal Bernstein
 */
public class StudentServiceImplTest extends UnitTestBase
{

    private StudentDAO studentDAO = Mockito.mock(StudentDAO.class, (Answer) (invocation) -> null);

    @Autowired
    private Student student;

    @Autowired
    private StudentServiceImpl studentService;

    @Before
    public void before()
    {
        this.studentService.setStudentDAO(this.studentDAO);
    }

    @Test
    public void update()
    {
        this.studentService.update(this.student);
    }
}
