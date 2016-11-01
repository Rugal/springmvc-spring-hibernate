package ga.rugal.sample.springmvc.controller;

import com.google.gson.Gson;
import ga.rugal.UnitTestBase;
import ga.rugal.sample.core.dao.RegistrationDAO;
import ga.rugal.sample.core.entity.Registration;
import ga.rugal.sample.core.service.RegistrationService;
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
public class RegistrationControllerTest extends UnitTestBase
{
    @Mock
    private RegistrationService registrationService;

    @Mock
    private RegistrationDAO registrationDAO;

    @Autowired
    private Gson GSON;

    @Autowired
    private Registration registration;

    @Autowired
    private RegistrationController registrationController;

    private MockMvc mockMvc;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        Mockito.when(this.registrationService.getDAO()).thenReturn(this.registrationDAO);
        this.registrationController.setRegistrationService(this.registrationService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.registrationController).build();
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void save_201() throws Exception
    {
        MvcResult result = this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.registration)))
            .andExpect(status().isCreated())
            .andReturn();
        Integer resultID = Integer.parseInt(result.getResponse().getContentAsString());
        Assert.assertEquals(resultID, this.registration.getRid());
    }

    @Test
    public void save_409() throws Exception
    {
        Mockito.when(this.registrationDAO.get(this.registration.getRid())).thenReturn(this.registration);
        MvcResult result = this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.registration)))
            .andExpect(status().isConflict())
            .andReturn();
        Integer resultID = Integer.parseInt(result.getResponse().getContentAsString());
        Assert.assertEquals(resultID, this.registration.getRid());
    }

    @Test
    public void update_204() throws Exception
    {
        Mockito.when(this.registrationDAO.get(this.registration.getRid())).thenReturn(this.registration);
        this.mockMvc.perform(put("/registration/{rid}", this.registration.getRid())
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.registration)))
            .andExpect(status().isNoContent());
    }

    @Test
    public void update_404() throws Exception
    {
        this.mockMvc.perform(put("/registration/{rid}", this.registration.getRid())
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.registration)))
            .andExpect(status().isNotFound());
    }

    @Test
    public void delete_204() throws Exception
    {
        Mockito.when(this.registrationDAO.get(this.registration.getRid())).thenReturn(this.registration);
        this.mockMvc.perform(delete("/registration/{rid}", this.registration.getRid()))
            .andExpect(status().isNoContent());
    }

    @Test
    public void delete_404() throws Exception
    {
        this.mockMvc.perform(delete("/registration/{rid}", this.registration.getRid()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void get_404() throws Exception
    {
        this.mockMvc.perform(get("/registration/{rid}", this.registration.getRid()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void get_200() throws Exception
    {
        Mockito.when(this.registrationDAO.get(this.registration.getRid())).thenReturn(this.registration);
        this.mockMvc.perform(get("/registration/{rid}", this.registration.getRid()))
            .andExpect(status().isOk());
    }
}
