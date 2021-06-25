package com.fastcampus.jaavallinone.project3.mycontact.service;

import static org.junit.jupiter.api.Assertions.*;

import com.fastcampus.jaavallinone.project3.mycontact.repository.BlockRepository;
import com.fastcampus.jaavallinone.project3.mycontact.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonServiceTest {

  @Autowired
  private PersonService personService;
  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private BlockRepository blockRepository;

  @Test
  void getPeopleExcludeBlocks() {

  }
}