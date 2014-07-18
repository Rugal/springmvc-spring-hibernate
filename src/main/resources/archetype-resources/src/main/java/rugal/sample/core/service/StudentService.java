package rugal.sample.core.service;

import ml.rugal.sshcommon.page.Pagination;
import rugal.sample.core.entity.Student;

/**
 *
 * @author Rugal Bernstein
 */
public interface StudentService
{

    Student deleteById(Integer id);

    Student findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    Student save(Student bean);

    Student update(Student bean);

}
