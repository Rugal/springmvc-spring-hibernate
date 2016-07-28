package rugal.sample.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rugal.sample.core.entity.Student;
import rugal.sample.core.service.StudentService;
import rugal.sample.core.dao.StudentDAO;

/**
 *
 * @author Rugal Bernstein
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService
{

    @Autowired
    private StudentDAO studentDao;

    @Override
    public Student update(Student bean)
    {
        Updater<Student> updater = new Updater<>(bean);
        return studentDao.updateByUpdater(updater);
    }

    @Override
    public StudentDAO getDAO()
    {
        return this.studentDao;
    }
}
