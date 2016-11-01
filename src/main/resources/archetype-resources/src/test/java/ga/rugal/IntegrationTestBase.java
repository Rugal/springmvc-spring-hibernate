package ga.rugal;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Rugal Bernstein
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =
{
    config.TestApplicationContext.class, config.ApplicationContext.class
})
@Ignore
public abstract class IntegrationTestBase
{
}
