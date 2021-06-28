package com.fastcampus.jaavallinone.project3.mycontact.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class HelloWorldControllerTest {

  @Autowired
  private HelloWorldController helloWorldController;

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;

  @BeforeEach
  void beforeEach() {
    mockMvc = MockMvcBuilders
        .webAppContextSetup(wac)
        .build();
  }

  @Test
  void helloWorld() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(helloWorldController).build();

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/helloWorld"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("HelloWorld"));
  }

  @Test
  void helloException() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/helloException"))
        .andExpect(status().isInternalServerError())
        .andExpect(jsonPath("$.code").value(500))
        .andExpect(jsonPath("$.message").value("알 수 없는 서버 오류가 발생하였습니다."));
  }
}