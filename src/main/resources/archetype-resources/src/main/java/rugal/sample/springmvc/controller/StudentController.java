package rugal.sample.springmvc.controller;

import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rugal.sample.core.entity.Student;
import rugal.sample.core.service.StudentService;

/**
 *
 * A sample controller class for GET/DELETE/POST/PUT.
 *
 * @author Rugal Bernstein
 */
@Slf4j
@Controller
@RequestMapping(value = "/student")
public class StudentController
{

    @Autowired
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
    public Integer save(@RequestBody Student bean, final HttpServletResponse response)
    {
        response.setStatus(HttpServletResponse.SC_CREATED);
        studentService.getDAO().save(bean);
        return bean.getId();
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
    public void update(@PathVariable("id") final Integer id, @RequestBody final Student bean,
                       final HttpServletResponse response)
    {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        if (null != studentService.getDAO().get(id))
        {
            bean.setId(id);
            studentService.update(bean);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    /**
     * DELETE a student record from database.
     *
     * @param id       the target student id.
     * @param response
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") final Integer id, final HttpServletResponse response)
    {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        final Student bean = studentService.getDAO().get(id);
        if (null != bean)
        {
            studentService.getDAO().delete(bean);
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
    public Object get(@PathVariable("id") final Integer id, final HttpServletResponse response)
    {
        final Student bean = studentService.getDAO().get(id);
        response.setStatus(bean == null ? HttpServletResponse.SC_NOT_FOUND : HttpServletResponse.SC_OK);
        return bean;
    }
}
