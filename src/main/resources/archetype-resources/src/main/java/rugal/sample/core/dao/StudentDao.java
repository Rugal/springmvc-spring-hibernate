package rugal.sample.core.dao;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import rugal.sample.core.entity.Student;

/**
 *
 * @author Rugal Bernstein
 */
public interface StudentDao
{

    Student delete(Student bean);

    Student get(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    Student save(Student bean);

    Student updateByUpdater(Updater<Student> updater);
}
