package ga.rugal.sample.core.service.impl;

import ga.rugal.sample.core.dao.StudentDAO;
import ga.rugal.sample.core.entity.Student;
import ga.rugal.sample.core.service.StudentService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ml.rugal.sshcommon.hibernate.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rugal Bernstein
 */
@Setter
@Service
@Slf4j
public class StudentServiceImpl implements StudentService
{

    @Autowired
    private StudentDAO studentDAO;

    @Override
    public StudentDAO getDAO()
    {
        return this.studentDAO;
    }

    @Override
    public Student update(Student student)
    {
        Updater<Student> updater = new Updater<>(student);
        return this.studentDAO.updateByUpdater(updater);
    }
}
