package ga.rugal.sample.core.dao.impl;

import ga.rugal.sample.core.dao.CourseDAO;
import ga.rugal.sample.core.entity.Course;
import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rugal Bernstein
 */
@Repository
public class CourseDAOImpl extends HibernateBaseDao<Course, Integer> implements CourseDAO
{

    @Override
    protected Class<Course> getEntityClass()
    {
        return Course.class;
    }
}
