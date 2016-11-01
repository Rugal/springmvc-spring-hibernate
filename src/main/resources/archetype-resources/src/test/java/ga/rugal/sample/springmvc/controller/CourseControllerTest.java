package ga.rugal.sample.springmvc.controller;

import com.google.gson.Gson;
import ga.rugal.UnitTestBase;
import ga.rugal.sample.core.dao.CourseDAO;
import ga.rugal.sample.core.entity.Course;
import ga.rugal.sample.core.service.CourseService;
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
public class CourseControllerTest extends UnitTestBase
{
    @Mock
    private CourseService courseService;

    @Mock
    private CourseDAO courseDAO;

    @Autowired
    private Gson GSON;

    @Autowired
    private Course course;

    @Autowired
    private CourseController courseController;

    private MockMvc mockMvc;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        Mockito.when(this.courseService.getDAO()).thenReturn(this.courseDAO);
        this.courseController.setCourseService(this.courseService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.courseController).build();
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void save_201() throws Exception
    {
        MvcResult result = this.mockMvc.perform(post("/course")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.course)))
            .andExpect(status().isCreated())
            .andReturn();
        Integer resultID = Integer.parseInt(result.getResponse().getContentAsString());
        Assert.assertEquals(resultID, this.course.getCid());
    }

    @Test
    public void save_409() throws Exception
    {
        Mockito.when(this.courseDAO.get(this.course.getCid())).thenReturn(this.course);
        MvcResult result = this.mockMvc.perform(post("/course")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.course)))
            .andExpect(status().isConflict())
            .andReturn();
        Integer resultID = Integer.parseInt(result.getResponse().getContentAsString());
        Assert.assertEquals(resultID, this.course.getCid());
    }

    @Test
    public void update_204() throws Exception
    {
        Mockito.when(this.courseDAO.get(this.course.getCid())).thenReturn(this.course);
        this.mockMvc.perform(put("/course/{cid}", this.course.getCid())
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.course)))
            .andExpect(status().isNoContent());
    }

    @Test
    public void update_404() throws Exception
    {
        this.mockMvc.perform(put("/course/{cid}", this.course.getCid())
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.course)))
            .andExpect(status().isNotFound());
    }

    @Test
    public void delete_204() throws Exception
    {
        Mockito.when(this.courseDAO.get(this.course.getCid())).thenReturn(this.course);
        this.mockMvc.perform(delete("/course/{cid}", this.course.getCid()))
            .andExpect(status().isNoContent());
    }

    @Test
    public void delete_404() throws Exception
    {
        this.mockMvc.perform(delete("/course/{cid}", this.course.getCid()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void get_404() throws Exception
    {
        this.mockMvc.perform(get("/course/{cid}", this.course.getCid()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void get_200() throws Exception
    {
        Mockito.when(this.courseDAO.get(this.course.getCid())).thenReturn(this.course);
        this.mockMvc.perform(get("/course/{cid}", this.course.getCid()))
            .andExpect(status().isOk());
    }
}
