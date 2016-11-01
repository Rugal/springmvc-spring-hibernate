package ga.rugal.sample.core.dao.impl;

import ga.rugal.sample.core.dao.StudentDAO;
import ga.rugal.sample.core.entity.Student;
import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rugal Bernstein
 */
@Repository
public class StudentDAOImpl extends HibernateBaseDao<Student, Integer> implements StudentDAO
{

    @Override
    protected Class<Student> getEntityClass()
    {
        return Student.class;
    }
}
