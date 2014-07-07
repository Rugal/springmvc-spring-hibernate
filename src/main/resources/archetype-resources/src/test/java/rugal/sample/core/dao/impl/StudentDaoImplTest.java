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
package rugal.sample.core.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import rugal.JUnitSpringTestBase;
import rugal.common.page.Pagination;
import rugal.sample.core.dao.StudentDao;
import rugal.sample.core.entity.Student;

/**
 *
 * @author rugal
 */
public class StudentDaoImplTest extends JUnitSpringTestBase
{

    @Autowired
    private StudentDao studentDao;

    public StudentDaoImplTest()
    {
    }

//    @Test
    public void testGetPage()
    {
        System.out.println("getPage");
        int pageNo = 0;
        int pageSize = 0;
        Pagination result = studentDao.getPage(pageNo, pageSize);
    }

    @Test
    public void testFindById()
    {
        System.out.println("findById");
        Integer id = 1;
        Student result = studentDao.findById(id);
    }

//    @Test
    public void testSave()
    {
        System.out.println("save");
        Student bean = new Student();
        bean.setId(1);
        bean.setAge(23);
        bean.setName("Rugal Bernstein");
        Student result = studentDao.save(bean);
    }

//    @Test
    public void testDeleteById()
    {
        System.out.println("deleteById");
        Integer id = 1;
        Student result = studentDao.deleteById(id);
    }

}
