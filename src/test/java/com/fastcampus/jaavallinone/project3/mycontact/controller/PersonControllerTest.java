package com.fastcampus.jaavallinone.project3.mycontact.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fastcampus.jaavallinone.project3.mycontact.controller.dto.PersonDto;
import com.fastcampus.jaavallinone.project3.mycontact.domain.Person;
import com.fastcampus.jaavallinone.project3.mycontact.domain.dto.Birthday;
import com.fastcampus.jaavallinone.project3.mycontact.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;

@Slf4j
@SpringBootTest
@Transactional
class PersonControllerTest {

  @Autowired
  private PersonController personController;

  private MockMvc mockMvc;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MappingJackson2HttpMessageConverter messageConverter;

  @BeforeEach
  void beforeEach() {
    mockMvc = MockMvcBuilders.standaloneSetup(personController)
        .setMessageConverters(messageConverter).build();
  }

  @Test
  void getPerson() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/person/1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("martin"))
        .andExpect(jsonPath("$.hobby").isEmpty())
        .andExpect(jsonPath("$.address").isEmpty())
        .andExpect(jsonPath("$.birthday").value("1991-08-15"))
        .andExpect(jsonPath("$.job").isEmpty())
        .andExpect(jsonPath("$.phoneNumber").isEmpty())
        .andExpect(jsonPath("$.deleted").value(false))
        .andExpect(jsonPath("$.age").isNumber())
        .andExpect(jsonPath("$.birthdayToday").isBoolean());
  }

  @Test
  void postPerson() throws Exception {
    PersonDto dto = PersonDto
        .of("martin", "programming", "판교", LocalDate.now(), "programmer", "010-1111-2222");

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/person")
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJsonString(dto)))
        .andDo(print())
        .andExpect(status().isCreated());

    Person result = personRepository.findAll(Sort.by(Direction.DESC, "id")).get(0);

    assertAll(
        () -> assertThat(result.getName()).isEqualTo("martin"),
        () -> assertThat(result.getHobby()).isEqualTo("programming"),
        () -> assertThat(result.getAddress()).isEqualTo("판교"),
        () -> assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())),
        () -> assertThat(result.getJob()).isEqualTo("programmer"),
        () -> assertThat(result.getPhoneNumber()).isEqualTo("010-1111-2222")
    );
  }

  @Test
  void modifyPerson() throws Exception {
    PersonDto dto = PersonDto
        .of("martin", "programming", "판교", LocalDate.now(), "programmer", "010-1111-2222");

    mockMvc.perform(
        MockMvcRequestBuilders.put("/api/person/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJsonString(dto)))
        .andDo(print())
        .andExpect(status().isOk());

    Person result = personRepository.findById(1L).get();

    assertAll(
        () -> assertThat(result.getName()).isEqualTo("martin"),
        () -> assertThat(result.getHobby()).isEqualTo("programming"),
        () -> assertThat(result.getAddress()).isEqualTo("판교"),
        () -> assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())),
        () -> assertThat(result.getJob()).isEqualTo("programmer"),
        () -> assertThat(result.getPhoneNumber()).isEqualTo("010-1111-2222")
    );
  }

  @Test
  void modifyPersonIfNameIsDifferent() throws Exception {
    PersonDto dto = PersonDto
        .of("james", "programming", "판교", LocalDate.now(), "programmer", "010-1111-2222");

    assertThrows(NestedServletException.class, () ->
        mockMvc.perform(
            MockMvcRequestBuilders.put("/api/person/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(dto)))
            .andDo(print())
            .andExpect(status().isOk()));
  }

  @Test
  void modifyName() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.patch("/api/person/1")
            .param("name", "martinModified"))
        .andDo(print())
        .andExpect(status().isOk());

    assertThat(personRepository.findById(1L).get().getName()).isEqualTo("martinModified");
  }

  @Test
  void deleteName() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.delete("/api/person/1"))
        .andDo(print())
        .andExpect(status().isOk());

    assertTrue(
        personRepository.findPeopleDelete().stream().anyMatch(person -> person.getId().equals(1L)));
  }

  private String toJsonString(PersonDto personDto) throws JsonProcessingException {
    return objectMapper.writeValueAsString(personDto);
  }
}