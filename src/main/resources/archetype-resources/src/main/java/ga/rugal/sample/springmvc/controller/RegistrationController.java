package ga.rugal.sample.springmvc.controller;

import ga.rugal.sample.core.entity.Registration;
import ga.rugal.sample.core.service.RegistrationService;
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
@RequestMapping("/registration")
@Slf4j
public class RegistrationController
{
    @Autowired
    @Setter
    private RegistrationService registrationService;

    /**
     * Persist a registration bean into database.
     *
     * @param bean     registration bean resembled from request body.
     * @param response
     *
     * @return ID of persisted bean.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Integer save(@RequestBody Registration bean, HttpServletResponse response)
    {
        response.setStatus(HttpServletResponse.SC_CONFLICT);
        Registration db = this.registrationService.getDAO().get(bean.getRid());
        if (null == db)
        {
            response.setStatus(HttpServletResponse.SC_CREATED);
            this.registrationService.getDAO().save(bean);
        }
        return bean.getRid();
    }

    /**
     * Update a registration bean.
     *
     * @param id       primary key of target registration.
     * @param bean     the newer registration bean
     * @param response
     *
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") Integer id, @RequestBody Registration bean, HttpServletResponse response)
    {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        if (null != this.registrationService.getDAO().get(id))
        {
            bean.setRid(id);
            this.registrationService.update(bean);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    /**
     * DELETE a registration record from database.
     *
     * @param id       the target registration id.
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id, HttpServletResponse response)
    {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        Registration bean = this.registrationService.getDAO().get(id);
        if (null != bean)
        {
            this.registrationService.getDAO().delete(bean);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    /**
     * GET a registration record from database.<BR>
     *
     * @param id       primary key of target registration.
     * @param response
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Integer id, HttpServletResponse response)
    {
        Registration bean = this.registrationService.getDAO().get(id);
        response.setStatus(bean == null ? HttpServletResponse.SC_NOT_FOUND : HttpServletResponse.SC_OK);
        return bean;
    }
}
