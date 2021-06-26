package com.fastcampus.jaavallinone.project3.mycontact.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class PersonControllerTest {

  @Autowired
  private PersonController personController;

  private MockMvc mockMvc;

  @Test
  void getPerson() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/person/1"))
        .andDo(print())
        .andExpect(status().isOk());
  }

}