/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.statusmgr;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
@ImportResource("classpath:applicationContext")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServerIMonitorableServerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("/server/status")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up"));
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {

        this.mockMvc.perform(get("/server/status").param("name", "RebYid"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by RebYid"));
    }
    @Test
    public void testDetailedOperations() throws Exception {

        this.mockMvc.perform(get("/server/status/detailed").param("details", "operations"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Anonymous"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally"));
    }
    @Test
    public void testDetailedMemory() throws Exception {

        this.mockMvc.perform(get("/server/status/detailed").param("details", "memory"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Anonymous"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and its memory is running low"));
    }
    @Test
    public void testDetailedExtensions() throws Exception {

        this.mockMvc.perform(get("/server/status/detailed").param("details", "extensions"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Anonymous"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is using these extensions - [Hypervisor, Kubernetes, RAID-6]"));
    }
    @Test
    public void testDetailedAllDetails() throws Exception {

        this.mockMvc.perform(get("/server/status/detailed").param("details", "operations,extensions,memory"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Anonymous"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally, and is using these extensions - [Hypervisor, Kubernetes, RAID-6], and its memory is running low"));
    }
    @Test
    public void testDetailedAllDetailsAndName() throws Exception {

        this.mockMvc.perform(get("/server/status/detailed").param("details", "operations,extensions,memory")
                .param("name", "Steven"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Steven"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally, and is using these extensions - [Hypervisor, Kubernetes, RAID-6], and its memory is running low"));
    }
    @Test
    public void testWhenDetailsIsNullException() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed")).andDo(print()).andExpect(status().is4xxClientError());

    }
    @Test
    public void testWhenDetailsIsInvalidException() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed").param("details", "foo")).andDo(print())
                .andExpect(status().is4xxClientError());

    }


}
