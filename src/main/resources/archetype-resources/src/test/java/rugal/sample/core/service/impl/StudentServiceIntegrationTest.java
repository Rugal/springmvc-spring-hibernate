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
package rugal.sample.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import rugal.JUnitSpringTestBase;
import rugal.sample.core.entity.Student;
import rugal.sample.core.service.StudentService;

/**
 *
 * @author rugal
 */
@Slf4j
public class StudentServiceIntegrationTest extends JUnitSpringTestBase
{

    @Autowired
    private StudentService studentService;

    @Autowired
    private Student bean;

    @Before
    public void setUp()
    {
        LOG.debug("setUp");
        studentService.getDAO().save(bean);
    }

    @After
    public void tearDown()
    {
        LOG.debug("tearDown");
        studentService.getDAO().delete(bean);
    }

    @Test
    @Ignore
    public void testUpdate()
    {
        LOG.debug("update");
        String newName = "Test";
        bean.setName(newName);
        studentService.update(bean);
        Student updated = studentService.getDAO().get(bean.getId());
        Assert.assertEquals(newName, updated.getName());
    }
}
