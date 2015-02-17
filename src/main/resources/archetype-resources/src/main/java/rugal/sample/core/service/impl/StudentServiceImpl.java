package rugal.sample.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rugal.sample.core.dao.StudentDao;
import rugal.sample.core.entity.Student;
import rugal.sample.core.service.StudentService;

/**
 *
 * @author Rugal Bernstein
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService
{

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student deleteById(Integer id)
    {
        //Used for transaction test
        return studentDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public Student findById(Integer id)
    {
        return studentDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        return studentDao.getPage(pageNo, pageSize);
    }

    @Override
    public Student save(Student bean)
    {
        //Used for transaction test
        return studentDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public Student update(Student bean)
    {
        Updater<Student> updater = new Updater<>(bean);
        return studentDao.updateByUpdater(updater);
    }

}
