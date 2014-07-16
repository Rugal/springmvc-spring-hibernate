package rugal.sample.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import rugal.ControllerClientSideTestBase;
import rugal.sample.common.Message;
import rugal.sample.core.entity.Student;

/**
 *
 * @author rugal
 */
public class StudentActionTest extends ControllerClientSideTestBase
{

    @Autowired
    private StudentAction studentAction;

    public StudentActionTest()
    {
    }

    @Test
    public void testRegisterStudent() throws Exception
    {
        System.out.println("registerStudent");
        this.mockMvc.perform(post("/student")
            .content("{\"id\":\"3\",\"name\":\"tenjin\",\"age\":\"23\"}")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        System.out.println("Rugal Bernstein");
    }

    //    @Test
    public void testUpdateStudentProfile()
    {
        System.out.println("updateStudentProfile");
        Integer id = null;
        Student bean = null;
        StudentAction instance = new StudentAction();
        Message expResult = null;
        Message result = instance.updateStudentProfile(id, bean);
    }

//    @Test
    public void testDeregister()
    {
        System.out.println("cancelOrder");
        Integer id = null;
        StudentAction instance = new StudentAction();
        Message expResult = null;
        Message result = instance.deregister(id);
    }

//    @Test
    public void testRetrieve()
    {
        System.out.println("retrieve");
        Integer id = null;
        StudentAction instance = new StudentAction();
        Message expResult = null;
        Message result = instance.retrieve(id);
    }

}
