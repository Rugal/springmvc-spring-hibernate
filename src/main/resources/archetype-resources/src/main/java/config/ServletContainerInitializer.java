/*
 * Copyright 2015 Administrator.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package config;

import javax.servlet.Filter;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Replace all XML file with Java based configuration only.
 * Servlet version must above 3.0
 * <p>
 * @author Rugal Bernstein
 * @since 0.6
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
