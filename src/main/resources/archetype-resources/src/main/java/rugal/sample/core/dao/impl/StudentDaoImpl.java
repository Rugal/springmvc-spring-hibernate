package rugal.sample.core.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import rugal.common.hibernate.HibernateBaseDao;
import rugal.common.page.Pagination;
import rugal.sample.core.dao.StudentDao;
import rugal.sample.core.entity.Student;

/**
 *
 * @author Rugal Bernstein
 */
@Repository
public class StudentDaoImpl extends HibernateBaseDao<Student, Integer> implements StudentDao
{

    @Override
    public Pagination getPage(int pageNo, int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public Student findById(Integer id)
    {
        Student entity = get(id);
        return entity;
    }

    @Override
    public Student save(Student bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Student deleteById(Integer id)
    {
//        throw new UnsupportedOperationException();
        Student entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Student> getEntityClass()
    {
        return Student.class;
    }

}
