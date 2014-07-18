package rugal;

import org.junit.Before;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author rugal
 */
@ContextConfiguration(classes = config.WebApplicationContext.class)
@WebAppConfiguration
@Ignore
public abstract class ControllerClientSideTestBase extends JUnitSpringTestBase
{

    @Autowired
    public WebApplicationContext wac;

    public MockMvc mockMvc;

    @Before
    public void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
}
