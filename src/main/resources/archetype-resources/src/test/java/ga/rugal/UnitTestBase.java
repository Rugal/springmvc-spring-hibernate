package ga.rugal;

import config.TestApplicationContext;
import config.UnitTestApplicationContext;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This is a base test class for non-controller class in testing stage<BR/>
 * with the assistance of this test base class, there is no need to redeploy web server over and over again, which is
 * too time wastage.<BR/>
 * any class that extends this class could have the capability to test without web server deployment
 * <p>
 * @author Rugal Bernstein
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =
{
    TestApplicationContext.class, UnitTestApplicationContext.class
})
@Ignore
public abstract class UnitTestBase
{
}
