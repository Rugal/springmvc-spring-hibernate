package ga.rugal.sample.springmvc.controller;

import com.google.gson.Gson;
import ga.rugal.ControllerClientSideTestBase;
import ga.rugal.sample.core.entity.Student;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Rugal Bernstein
 */
public class StudentControllerIntegrationTest extends ControllerClientSideTestBase
{

    @Autowired
    private Gson GSON;

    @Autowired
    private Student student;

    @Before
    public void setUp() throws Exception
    {
        this.save_201();
    }

    @After
    public void tearDown() throws Exception
    {
        this.delete_204();
    }

    private void save_201() throws Exception
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
        this.mockMvc.perform(put("/student/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.student)))
            .andExpect(status().isNoContent());
    }

    @Test
    public void update_404() throws Exception
    {
        this.mockMvc.perform(put("/student/{sid}", 0)
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.student)))
            .andExpect(status().isNotFound());
    }

    private void delete_204() throws Exception
    {
        this.mockMvc.perform(delete("/student/{sid}", this.student.getSid()))
            .andExpect(status().isNoContent());
    }

    @Test
    public void delete_404() throws Exception
    {
        this.mockMvc.perform(delete("/student/{sid}", 0))
            .andExpect(status().isNotFound());
    }

    @Test
    public void get_200() throws Exception
    {
        this.mockMvc.perform(get("/student/{sid}", this.student.getSid()))
            .andExpect(status().isOk());
    }

    @Test
    public void get_404() throws Exception
    {
        this.mockMvc.perform(get("/student/{sid}", 0))
            .andExpect(status().isNotFound());
    }
}
