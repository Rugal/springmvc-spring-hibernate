package config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * This is Servlet initializer class replace for web.xml file.
 *
 * @author Rugal Bernstein
 * @since 0.6
 */
public class ServletContainerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException
    {
        servletContext.addFilter("openSessionInViewFilter", openSessionInViewFilter()).addMappingForUrlPatterns(null, false, "/*");
        servletContext.addFilter("characterEncodingFilter", characterEncodingFilter()).addMappingForUrlPatterns(null, true, "/*");
        servletContext.addFilter("hiddenHttpMethodFilter", hiddenHttpMethodFilter()).addMappingForUrlPatterns(null, true, "/*");
        super.onStartup(servletContext);
    }


    /**
     * Common configuration.
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[]
        {
            ApplicationContext.class
        };
    }

    /**
     * SpringMVC configuration.
     *
     * @return
     */
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

    private Filter openSessionInViewFilter()
    {
        return new OpenSessionInViewFilter();
    }

    private Filter characterEncodingFilter()
    {
        CharacterEncodingFilter cef = new CharacterEncodingFilter();
        cef.setEncoding(SystemDefaultProperties.ENCODING);
        cef.setForceEncoding(true);
        return cef;
    }

    private Filter hiddenHttpMethodFilter()
    {
        return new HiddenHttpMethodFilter();
    }
}
