package ga.rugal.sample.core.service;

import ga.rugal.sample.core.dao.CourseDAO;
import ga.rugal.sample.core.entity.Course;
import ga.rugal.sample.core.service.BaseService;

/**
 *
 * @author Rugal Bernstein
 */
public interface CourseService extends BaseService<CourseDAO>
{

    Course update(Course course);

}
