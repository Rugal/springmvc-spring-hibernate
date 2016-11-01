package ga.rugal.sample.core.dao;

import ga.rugal.sample.core.entity.Course;
import ml.rugal.sshcommon.hibernate.Updater;

/**
 *
 * @author Rugal Bernstein
 */
public interface CourseDAO
{

    Course get(Integer cid);

    Course delete(Course course);

    Course save(Course course);

    Course updateByUpdater(Updater<Course> updater);
}
