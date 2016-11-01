package ga.rugal.sample.core.dao;

import ga.rugal.sample.core.entity.Student;
import ml.rugal.sshcommon.hibernate.Updater;

/**
 *
 * @author Rugal Bernstein
 */
public interface StudentDAO
{

    Student get(Integer sid);

    Student delete(Student student);

    Student save(Student student);

    Student updateByUpdater(Updater<Student> updater);
}
