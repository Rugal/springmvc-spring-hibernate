package rugal.common.hibernate;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import rugal.common.util.BeanUtils;

/**
 *
 * An abstract hibernate DAO class that implement the HibernateSimpleDao, implemented its get and find method. <BR/>
 * In addition to this, this class provide abstract update method.
 *
 * @author Rugal Bernstein
 * @param <T>  This is the entity class for finder reflection
 * @param <ID> This is the ID type of entity class.
 */
@Transactional
public abstract class HibernateBaseDao<T, ID extends Serializable> extends HibernateSimpleDao
{

    /**
     * @see Session.get(Class,Serializable)
     * @param id primary key to criteria for.
     * @return get reflected object.
     */
    protected T get(ID id)
    {
        return get(id, false);
    }

    /**
     * @see Session.get(Class,Serializable,LockMode)
     * @param id   primary key to criteria for.
     * @param lock set lock mode in query
     * @return get reflected object.
     */
    protected T get(ID id, boolean lock)
    {
        T entity;
        if (lock)
        {
            entity = (T) getSession().get(getEntityClass(), id, LockMode.UPGRADE);
        } else
        {
            entity = (T) getSession().get(getEntityClass(), id);
        }
        return entity;
    }

    /**
     * Query for list of matched object by given properties.
     *
     * @param property
     * @param value
     * @return
     */
    protected List<T> findByProperty(String property, Object value)
    {
        Assert.hasText(property);
        return createCriteria(Restrictions.eq(property, value)).list();
    }

    /**
     * Query for list of matched object by given string data.
     * For this is front matching method. <BR/>
     * Pattern: (value%)
     *
     * @param property
     * @param value
     * @return
     */
    protected List<T> findByPropertyBefore(String property, Object value)
    {
        Assert.hasText(property);
        return createCriteria(Restrictions.like(property, value + "%")).list();
    }

    /**
     * Query for list of matched object by given string data.
     * For this is vague search.<BR/>
     * Pattern: (%value%)
     *
     * @param property
     * @param value
     * @return
     */
    protected List<T> findByPropertyVague(String property, Object value)
    {
        Assert.hasText(property);
        return createCriteria(Restrictions.like(property, "%" + value + "%")).list();
    }

    /**
     * Query for list of matched object by given string data.
     * For this is backend matching method.<BR/>
     * Pattern: (%value)
     *
     * @param property
     * @param value
     * @return
     */
    protected List<T> findByPropertyAfter(String property, Object value)
    {
        Assert.hasText(property);
        return createCriteria(Restrictions.like(property, "%" + value)).list();
    }

    /**
     * Query for unique object of matched by given criteria.
     *
     * @param property
     * @param value
     * @return
     */
    protected T findUniqueByProperty(String property, Object value)
    {
        Assert.hasText(property);
        Assert.notNull(value);
        return (T) createCriteria(Restrictions.eq(property, value)).uniqueResult();
    }

    /**
     * This method count quantity of returned row filtered by property.
     *
     * @param property The given property name
     * @param value    The given property value
     * @return matched record number.
     */
    protected int countByProperty(String property, Object value)
    {
        Assert.hasText(property);
        Assert.notNull(value);
        return ((Number) (createCriteria(
            Restrictions.eq(property, value)).setProjection(
                Projections.rowCount()).uniqueResult())).intValue();
    }

    /**
     * Similar to query, but predicate with criteria.
     *
     * @param criterion
     * @return
     */
    protected List findByCriteria(Criterion... criterion)
    {
        return createCriteria(criterion).list();
    }

    /**
     * To update entity table by bean updater.
     *
     * @param updater
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public T updateByUpdater(Updater<T> updater)
    {
        ClassMetadata cm = sessionFactory.getClassMetadata(
            getEntityClass());
        T bean = updater.getBean();
        T po = (T) getSession().get(getEntityClass(),
            cm.getIdentifier(bean));
        updaterCopyToPersistentObject(updater, po, cm);
        return po;
    }

    /**
     * Update and copy field information into persistent object of database.
     *
     * @param updater
     * @param po
     */
    private void updaterCopyToPersistentObject(Updater<T> updater, T po,
        ClassMetadata cm)
    {
        String[] propNames = cm.getPropertyNames();
        String identifierName = cm.getIdentifierPropertyName();
        T bean = updater.getBean();
        Object value;
        for (String propName : propNames)
        {
            if (propName.equals(identifierName))
            {
                continue;
            }
            try
            {
                value = BeanUtils.getSimpleProperty(bean,
                    propName);
                if (!updater.isUpdate(propName, value))
                {
                    continue;
                }
                cm.setPropertyValue(po, propName, value);
            } catch (IllegalArgumentException | SecurityException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | HibernateException e)
            {
                throw new RuntimeException(
                    "copy property to persistent object failed: '"
                    + propName + "'", e);
            }
        }
    }

    /**
     * Create a criteria to filter.
     *
     * @param criterions
     * @return
     */
    protected Criteria createCriteria(Criterion... criterions)
    {
        Criteria criteria = getSession().createCriteria(getEntityClass());
        for (Criterion c : criterions)
        {
            criteria.add(c);
        }
        return criteria;
    }

    /**
     * This method could used for reflection in later funtionality.
     *
     * @return return current entity class.
     */
    abstract protected Class<T> getEntityClass();
}
