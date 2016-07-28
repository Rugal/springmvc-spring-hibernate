/*
 * Copyright 2014 rugal.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rugal.sample.springmvc.controller;

import com.google.gson.Gson;
import config.SystemDefaultProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import rugal.ControllerClientSideTestBase;
import rugal.sample.core.entity.Student;
import rugal.sample.core.service.StudentService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 *
 * @author Rugal Bernstein
 * @since 0.7
 */
@Slf4j
public class StudentControllerIntegrationTest extends ControllerClientSideTestBase
{

    @Autowired
    private Gson GSON;

    @Autowired
    private StudentService studentService;

    @Autowired
    private Student bean;

    @Test
    public void post_201() throws Exception
    {
        LOG.debug("post_201");
        studentService.getDAO().delete(bean);
        final String json = GSON.toJson(bean);
        this.mockMvc.perform(post("/student")
            .header(SystemDefaultProperties.ID, "rugal")
            .header(SystemDefaultProperties.CREDENTIAL, "123")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated());
        studentService.getDAO().delete(bean);
    }

    @Test
    public void get_200() throws Exception
    {
        LOG.debug("get_200");
        studentService.getDAO().save(bean);
        this.mockMvc.perform(get("/student/" + bean.getId())
            .header(SystemDefaultProperties.ID, "rugal")
            .header(SystemDefaultProperties.CREDENTIAL, "123")
            .contentType(MediaType.TEXT_PLAIN)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
        studentService.getDAO().delete(bean);
    }

    @Test
    public void get_401() throws Exception
    {
        LOG.debug("get_401");
        this.mockMvc.perform(get("/student/999")
            .contentType(MediaType.TEXT_PLAIN)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isUnauthorized());
    }

    @Test
    public void get_404() throws Exception
    {
        LOG.debug("get_404");
        this.mockMvc.perform(get("/student/999")
            .header(SystemDefaultProperties.ID, "rugal")
            .header(SystemDefaultProperties.CREDENTIAL, "123")
            .contentType(MediaType.TEXT_PLAIN)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    public void delete_204() throws Exception
    {
        LOG.debug("delete_204");
        studentService.getDAO().save(bean);
        this.mockMvc.perform(delete("/student/" + bean.getId())
            .header(SystemDefaultProperties.ID, "rugal")
            .header(SystemDefaultProperties.CREDENTIAL, "123")
            .contentType(MediaType.TEXT_PLAIN)
            .accept(MediaType.TEXT_PLAIN))
            .andDo(print())
            .andExpect(status().isNoContent());
    }

    @Test
    public void delete_404() throws Exception
    {
        LOG.debug("delete_404");
        this.mockMvc.perform(delete("/student/999")
            .header(SystemDefaultProperties.ID, "rugal")
            .header(SystemDefaultProperties.CREDENTIAL, "123")
            .contentType(MediaType.TEXT_PLAIN)
            .accept(MediaType.TEXT_PLAIN))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    public void put_204() throws Exception
    {
        LOG.debug("put_204");
        studentService.getDAO().save(bean);
        final String newName = "test";
        bean.setName(newName);
        this.mockMvc.perform(put("/student/" + bean.getId())
            .header(SystemDefaultProperties.ID, "rugal")
            .header(SystemDefaultProperties.CREDENTIAL, "123")
            .content(GSON.toJson(bean))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNoContent());
        final Student updated = studentService.getDAO().get(bean.getId());
        Assert.assertEquals(newName, updated.getName());
        studentService.getDAO().delete(bean);
    }

    @Test
    public void put_404() throws Exception
    {
        LOG.debug("put_404");
        this.mockMvc.perform(put("/student/999")
            .header(SystemDefaultProperties.ID, "rugal")
            .header(SystemDefaultProperties.CREDENTIAL, "123")
            .content(GSON.toJson(bean))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.TEXT_PLAIN))
            .andDo(print())
            .andExpect(status().isNotFound());
    }
}
