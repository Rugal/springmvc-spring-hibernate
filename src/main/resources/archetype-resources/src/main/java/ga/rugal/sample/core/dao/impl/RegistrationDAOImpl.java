package ga.rugal.sample.core.dao.impl;

import ga.rugal.sample.core.dao.RegistrationDAO;
import ga.rugal.sample.core.entity.Registration;
import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rugal Bernstein
 */
@Repository
public class RegistrationDAOImpl extends HibernateBaseDao<Registration, Integer> implements RegistrationDAO
{

    @Override
    protected Class<Registration> getEntityClass()
    {
        return Registration.class;
    }
}
