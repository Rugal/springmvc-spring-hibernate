package ga.rugal.sample.core.service.impl;

import ga.rugal.UnitTestBase;
import ga.rugal.sample.core.dao.RegistrationDAO;
import ga.rugal.sample.core.entity.Registration;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Rugal Bernstein
 */
public class RegistrationServiceImplTest extends UnitTestBase
{

    private RegistrationDAO registrationDAO = Mockito.mock(RegistrationDAO.class, (Answer) (invocation) -> null);

    @Autowired
    private Registration registration;

    @Autowired
    private RegistrationServiceImpl courseService;

    @Before
    public void before()
    {
        this.courseService.setRegistrationDAO(this.registrationDAO);
    }

    @Test
    public void persist_update()
    {
        this.courseService.update(this.registration);
    }
}
