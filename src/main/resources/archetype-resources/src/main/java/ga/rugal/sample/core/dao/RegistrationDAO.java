package ga.rugal.sample.core.dao;

import ga.rugal.sample.core.entity.Registration;
import ml.rugal.sshcommon.hibernate.Updater;

/**
 *
 * @author Rugal Bernstein
 */
public interface RegistrationDAO
{

    Registration get(Integer cid);

    Registration delete(Registration registration);

    Registration save(Registration registration);

    Registration updateByUpdater(Updater<Registration> updater);
}
