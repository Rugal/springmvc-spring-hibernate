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
 * This is a base test class for testing client side view.<BR/>
 * The test will go through everything from context to request parsing except launching a Servlet
 * container.<BR>
 * This test will experience very real like life cycle without launching container, hence, it is
 * highly consistent with real server but extremely good in performance.
 *
 * @author Rugal Bernstein
 * @since 0.5
 */
@ContextConfiguration(classes = config.SpringMVCApplicationContext.class)
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
