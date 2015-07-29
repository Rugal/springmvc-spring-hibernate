/*
 * Copyright 2014 rugal.
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
package rugal.sample.springmvc.controller;

import java.util.HashMap;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import rugal.ControllerServerSideTestBase;
import rugal.sample.core.entity.Student;

/**
 *
 * @author Rugal Bernstein
 */
public class StudentActionServerSideTest extends ControllerServerSideTestBase
{

    @Autowired
    private StudentAction studentAction;

    @Test
    @Ignore
    public void registerStudent()
    {
        request.setRequestURI("/student");
        request.setMethod(HttpMethod.POST.name());
        request.setContentType("application/json");

        String json = "{\"id\":\"2\",\"name\":\"tenjin\",\"age\":\"23\"}";
        request.setContent(json.getBytes());
        Class<?>[] parameterTypes = new Class<?>[]
        {
            Student.class
        };
        ModelAndView mv = null;
        try
        {
            mv = handlerAdapter
                .handle(request, response, new HandlerMethod(studentAction, "registerStudent", parameterTypes));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    @Ignore
    public void getAddress()
    {
        request.setMethod(HttpMethod.GET.name());
        request.setRequestURI("/student/{id}");
        HashMap<String, String> pathVariablesMap = new HashMap<>(1);
        pathVariablesMap.put("id", "3");
        request.setAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, pathVariablesMap);
        Class<?>[] parameterTypes = new Class<?>[]
        {
            Integer.class
        };
        ModelAndView mv = null;
        try
        {
            mv = handlerAdapter
                .handle(request, response, new HandlerMethod(studentAction, "retrieve", parameterTypes));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
