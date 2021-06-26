package com.fastcampus.jaavallinone.project3.mycontact.controller;

import com.fastcampus.jaavallinone.project3.mycontact.controller.dto.PersonDto;
import com.fastcampus.jaavallinone.project3.mycontact.domain.Person;
import com.fastcampus.jaavallinone.project3.mycontact.repository.PersonRepository;
import com.fastcampus.jaavallinone.project3.mycontact.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/person")
@RestController
@Slf4j
public class PersonController {

  @Autowired
  private PersonService personService;

  @Autowired
  private PersonRepository personRepository;

  @GetMapping("/{id}")
  public Person getPerson(@PathVariable Long id) {
    return personService.getPerson(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void postPerson(@RequestBody Person person) {
    personService.put(person);
    log.info("person -> {}", personRepository.findAll());
  }

  @PutMapping("/{id}")
  public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto personDto){
    personService.modify(id, personDto);

    log.info("personDto -> {}", personRepository.findAll());
  }

  @PatchMapping("/{id}")
  public void modifyPerson(@PathVariable Long id, String name){
    personService.modify(id, name);

    log.info("personDto -> {}", personRepository.findAll());
  }

}
