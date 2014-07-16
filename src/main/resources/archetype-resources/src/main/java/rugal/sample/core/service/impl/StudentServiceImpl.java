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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rugal.common.hibernate.Updater;
import rugal.common.page.Pagination;
import rugal.sample.core.dao.StudentDao;
import rugal.sample.core.entity.Student;
import rugal.sample.core.service.StudentService;

/**
 *
 * @author Rugal Bernstein
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService
{

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student deleteById(Integer id)
    {
        //Used for transaction test
        return studentDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public Student findById(Integer id)
    {
        return studentDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        return studentDao.getPage(pageNo, pageSize);
    }

    @Override
    public Student save(Student bean)
    {
        //Used for transaction test
        return studentDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public Student update(Student bean)
    {
        Updater<Student> updater = new Updater<>(bean);
        return studentDao.updateByUpdater(updater);
    }

}
