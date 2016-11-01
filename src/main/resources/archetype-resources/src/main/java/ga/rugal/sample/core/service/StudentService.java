package ga.rugal.sample.core.service;

import ga.rugal.sample.core.dao.StudentDAO;
import ga.rugal.sample.core.entity.Student;

/**
 *
 * @author Rugal Bernstein
 */
public interface StudentService extends BaseService<StudentDAO>
{

    Student update(Student student);
}
