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
public class StudentServiceImplTest extends JUnitSpringTestBase
{

    @Autowired
    private StudentService studentService;

    public StudentServiceImplTest()
    {
    }

//    @Test
    public void testDeleteById()
    {
        System.out.println("deleteById");
        Integer id = null;
        StudentServiceImpl instance = new StudentServiceImpl();
        Student expResult = null;
        Student result = studentService.deleteById(id);
    }

    @Test
    @Ignore
    public void testFindById()
    {
        System.out.println("findById");
        Integer id = 1;
        studentService.findById(id);
    }

    @Test
//    @Ignore
    public void testSave()
    {
        System.out.println("save");
        Student bean = new Student();
        bean.setId(2);
        bean.setAge(132);
        bean.setName("Rugal");
        studentService.save(bean);
    }

}
