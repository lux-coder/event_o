package com.example.evento.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EventsControllerIntegrationTest {

   /* @Autowired
    MockMvc mockMvc;

    @Test
    public void whenEventExists_thenGetSuccess() throws Exception {
        mockMvc.perform(get("events/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("prvi event"));
    }*/

}
