package config;

import javax.servlet.Filter;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author Rugal Bernstein
 */
public class ServletContainerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{

    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[]
        {
            ApplicationContext.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class[]
        {
            SpringMVCApplicationContext.class
        };
    }

    @Override
    protected String[] getServletMappings()
    {
        return new String[]
        {
            "/"
        };
    }

    @Override
    protected String getServletName()
    {
        return "springmvc";
    }

    @Override
    protected boolean isAsyncSupported()
    {
        return true;
    }

    @Override
    protected Filter[] getServletFilters()
    {
        CharacterEncodingFilter cef = new CharacterEncodingFilter();
        cef.setEncoding("UTF-8");
        return new Filter[]
        {
            new OpenSessionInViewFilter(), new HiddenHttpMethodFilter(), cef
        };
    }
}
