package ga.rugal.sample.springmvc.controller;

import com.google.gson.Gson;
import ga.rugal.UnitTestBase;
import ga.rugal.sample.core.dao.StudentDAO;
import ga.rugal.sample.core.entity.Student;
import ga.rugal.sample.core.service.StudentService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author Rugal Bernstein
 */
public class StudentControllerTest extends UnitTestBase
{
    @Mock
    private StudentService studentService;

    @Mock
    private StudentDAO studentDAO;

    @Autowired
    private Gson GSON;

    @Autowired
    private Student student;

    @Autowired
    private StudentController studentController;

    private MockMvc mockMvc;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        Mockito.when(this.studentService.getDAO()).thenReturn(this.studentDAO);
        this.studentController.setStudentService(this.studentService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.studentController).build();
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void save_201() throws Exception
    {
        MvcResult result = this.mockMvc.perform(post("/student")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.student)))
            .andExpect(status().isCreated())
            .andReturn();
        Integer resultID = Integer.parseInt(result.getResponse().getContentAsString());
        Assert.assertEquals(resultID, this.student.getSid());
    }

    @Test
    public void save_409() throws Exception
    {
        Mockito.when(this.studentDAO.get(this.student.getSid())).thenReturn(this.student);
        MvcResult result = this.mockMvc.perform(post("/student")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.student)))
            .andExpect(status().isConflict())
            .andReturn();
        Integer resultID = Integer.parseInt(result.getResponse().getContentAsString());
        Assert.assertEquals(resultID, this.student.getSid());
    }

    @Test
    public void update_204() throws Exception
    {
        Mockito.when(this.studentDAO.get(this.student.getSid())).thenReturn(this.student);
        this.mockMvc.perform(put("/student/{sid}", this.student.getSid())
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.student)))
            .andExpect(status().isNoContent());
    }

    @Test
    public void update_404() throws Exception
    {
        this.mockMvc.perform(put("/student/{sid}", this.student.getSid())
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.student)))
            .andExpect(status().isNotFound());
    }

    @Test
    public void delete_204() throws Exception
    {
        Mockito.when(this.studentDAO.get(this.student.getSid())).thenReturn(this.student);
        this.mockMvc.perform(delete("/student/{sid}", this.student.getSid()))
            .andExpect(status().isNoContent());
    }

    @Test
    public void delete_404() throws Exception
    {
        this.mockMvc.perform(delete("/student/{sid}", this.student.getSid()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void get_404() throws Exception
    {
        this.mockMvc.perform(get("/student/{sid}", this.student.getSid()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void get_200() throws Exception
    {
        Mockito.when(this.studentDAO.get(this.student.getSid())).thenReturn(this.student);
        this.mockMvc.perform(get("/student/{sid}", this.student.getSid()))
            .andExpect(status().isOk());
    }
}
