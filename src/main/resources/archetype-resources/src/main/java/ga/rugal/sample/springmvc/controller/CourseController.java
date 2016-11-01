package ga.rugal.sample.springmvc.controller;

import ga.rugal.sample.core.entity.Course;
import ga.rugal.sample.core.service.CourseService;
import javax.servlet.http.HttpServletResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Rugal Bernstein
 */
@Controller
@RequestMapping("/course")
@Slf4j
public class CourseController
{
    @Autowired
    @Setter
    private CourseService courseService;

    /**
     * Persist a course bean into database.
     *
     * @param bean     course bean resembled from request body.
     * @param response
     *
     * @return ID of persisted bean.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Integer save(@RequestBody Course bean, HttpServletResponse response)
    {
        response.setStatus(HttpServletResponse.SC_CONFLICT);
        Course db = this.courseService.getDAO().get(bean.getCid());
        if (null == db)
        {
            response.setStatus(HttpServletResponse.SC_CREATED);
            this.courseService.getDAO().save(bean);
        }
        return bean.getCid();
    }

    /**
     * Update a course bean.
     *
     * @param id       primary key of target course.
     * @param bean     the newer course bean
     * @param response
     *
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") Integer id, @RequestBody Course bean, HttpServletResponse response)
    {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        if (null != this.courseService.getDAO().get(id))
        {
            bean.setCid(id);
            this.courseService.update(bean);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    /**
     * DELETE a course record from database.
     *
     * @param id       the target course id.
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id, HttpServletResponse response)
    {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        Course bean = this.courseService.getDAO().get(id);
        if (null != bean)
        {
            this.courseService.getDAO().delete(bean);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    /**
     * GET a course record from database.<BR>
     *
     * @param id       primary key of target course.
     * @param response
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Integer id, HttpServletResponse response)
    {
        Course bean = this.courseService.getDAO().get(id);
        response.setStatus(bean == null ? HttpServletResponse.SC_NOT_FOUND : HttpServletResponse.SC_OK);
        return bean;
    }
}
