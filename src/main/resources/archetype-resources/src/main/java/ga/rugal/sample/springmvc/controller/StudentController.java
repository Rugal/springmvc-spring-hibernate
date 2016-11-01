package ga.rugal.sample.springmvc.controller;

import ga.rugal.sample.core.entity.Student;
import ga.rugal.sample.core.service.StudentService;
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
@RequestMapping("/student")
@Slf4j
public class StudentController
{
    @Autowired
    @Setter
    private StudentService studentService;

    /**
     * Persist a student bean into database.
     *
     * @param bean     student bean resembled from request body.
     * @param response
     *
     * @return ID of persisted bean.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Integer save(@RequestBody Student bean, HttpServletResponse response)
    {
        response.setStatus(HttpServletResponse.SC_CONFLICT);
        Student db = this.studentService.getDAO().get(bean.getSid());
        if (null == db)
        {
            response.setStatus(HttpServletResponse.SC_CREATED);
            this.studentService.getDAO().save(bean);
        }
        return bean.getSid();
    }

    /**
     * Update a student bean.
     *
     * @param id       primary key of target student.
     * @param bean     the newer student bean
     * @param response
     *
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") Integer id, @RequestBody Student bean, HttpServletResponse response)
    {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        if (null != this.studentService.getDAO().get(id))
        {
            bean.setSid(id);
            this.studentService.update(bean);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    /**
     * DELETE a student record from database.
     *
     * @param id       the target student id.
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id, HttpServletResponse response)
    {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        Student bean = this.studentService.getDAO().get(id);
        if (null != bean)
        {
            this.studentService.getDAO().delete(bean);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    /**
     * GET a student record from database.<BR>
     *
     * @param id       primary key of target student.
     * @param response
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Integer id, HttpServletResponse response)
    {
        Student bean = this.studentService.getDAO().get(id);
        response.setStatus(bean == null ? HttpServletResponse.SC_NOT_FOUND : HttpServletResponse.SC_OK);
        return bean;
    }
}
