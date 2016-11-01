package ga.rugal.sample.core.service;

import ga.rugal.sample.core.dao.RegistrationDAO;
import ga.rugal.sample.core.entity.Registration;

/**
 *
 * @author Rugal Bernstein
 */
public interface RegistrationService extends BaseService<RegistrationDAO>
{

    Registration update(Registration registration);

}
