package rugal.sample.core.dao.impl;

import lombok.extern.slf4j.Slf4j;
import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import org.springframework.stereotype.Repository;
import rugal.sample.core.dao.StudentDao;
import rugal.sample.core.entity.Student;

/**
 *
 * @author Rugal Bernstein
 */
@Slf4j
@Repository
public class StudentDaoImpl extends HibernateBaseDao<Student, Integer> implements StudentDao
{

    @Override
    protected Class<Student> getEntityClass()
    {
        return Student.class;
    }
}
