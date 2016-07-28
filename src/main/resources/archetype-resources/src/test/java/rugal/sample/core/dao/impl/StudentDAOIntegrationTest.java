package rugal.sample.core.dao.impl;

import lombok.extern.slf4j.Slf4j;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import rugal.JUnitSpringTestBase;
import rugal.sample.core.dao.StudentDAO;
import rugal.sample.core.entity.Student;

/**
 *
 * @author rugal
 */
@Slf4j
public class StudentDAOIntegrationTest extends JUnitSpringTestBase
{

    @Autowired
    private Student bean;

    @Autowired
    private StudentDAO dao;

    @Before
    public void setUp()
    {
        LOG.debug("setUp");
        dao.save(bean);
    }

    @After
    public void tearDown()
    {
        LOG.debug("tearDown");
        dao.delete(bean);
    }

    @Test
    public void testGetPage()
    {
        LOG.debug("getPage");
        int pageNo = 0;
        int pageSize = 10;
        Pagination result = dao.getPage(pageNo, pageSize);
        Assert.assertFalse(result.getList().isEmpty());
    }

    @Test
    public void testGet()
    {
        LOG.debug("get");
        Integer id = bean.getId();
        Student result = dao.get(id);
        Assert.assertNotNull(result);
    }

    @Test
    public void testUpdate()
    {
        LOG.debug("update");
        String newName = "Test";
        bean.setName(newName);
        dao.updateByUpdater(new Updater<>(bean));
        Student updated = dao.get(bean.getId());
        Assert.assertEquals(newName, updated.getName());
    }
}
