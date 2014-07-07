package rugal.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rugal.sample.common.CommonMessageContent;
import rugal.sample.common.Message;
import rugal.sample.core.entity.Student;
import rugal.sample.core.service.StudentService;

@Controller
@RequestMapping(value = "/student")
public class StudentAction
{

    private static final Logger LOG = LoggerFactory.getLogger(StudentAction.class.getName());

    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Message registerStudent(@RequestBody Student bean)
    {
        studentService.save(bean);
        /*
         * Now we need to push message notify them
         */
        return Message.successMessage(CommonMessageContent.SAVE_SUCCEED, bean);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Message updateStudentProfile(@PathVariable("id") Integer id, @RequestBody Student bean)
    {
        Student dbStudent = studentService.findById(id);
        if (null != dbStudent)
        {
            studentService.update(bean);
        }
        /*
         * Here we need to push message to client
         */
        return Message.successMessage(CommonMessageContent.PROFILE_UPDATED, bean);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Message cancelOrder(@PathVariable("id") Integer id)
    {
        Student bean = studentService.findById(id);
        if (null != bean)
        {
            studentService.deleteById(id);
        }
        return Message.successMessage(CommonMessageContent.STUDENT_DELETED, bean);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Message retrieve(@PathVariable("id") Integer id)
    {
        Student bean = studentService.findById(id);
        return Message.successMessage(CommonMessageContent.GET_STUDENT, bean);
    }
}
