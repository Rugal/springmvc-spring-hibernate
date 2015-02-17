package rugal.sample.core.dao.impl;

import ml.rugal.sshcommon.page.Pagination;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import rugal.JUnitSpringTestBase;
import rugal.sample.core.dao.StudentDao;
import rugal.sample.core.entity.Student;

/**
 *
 * @author rugal
 */
public class StudentDaoImplTest extends JUnitSpringTestBase
{

    @Autowired
    private StudentDao studentDao;

    public StudentDaoImplTest()
    {
    }

//    @Test
    public void testGetPage()
    {
        System.out.println("getPage");
        int pageNo = 0;
        int pageSize = 0;
        Pagination result = studentDao.getPage(pageNo, pageSize);
    }

    @Test
    public void testFindById()
    {
        System.out.println("findById");
        Integer id = 1;
        Student result = studentDao.findById(id);
    }

//    @Test
    public void testSave()
    {
        System.out.println("save");
        Student bean = new Student();
        bean.setId(1);
        bean.setAge(23);
        bean.setName("Rugal Bernstein");
        Student result = studentDao.save(bean);
    }

//    @Test
    public void testDeleteById()
    {
        System.out.println("deleteById");
        Integer id = 1;
        Student result = studentDao.deleteById(id);
    }

}
