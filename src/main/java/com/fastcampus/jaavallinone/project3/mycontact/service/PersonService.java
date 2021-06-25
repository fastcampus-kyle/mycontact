package com.fastcampus.jaavallinone.project3.mycontact.service;

import com.fastcampus.jaavallinone.project3.mycontact.domain.Person;
import com.fastcampus.jaavallinone.project3.mycontact.repository.PersonRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class PersonService {

  @Autowired
  private PersonRepository personRepository;

  public List<Person> getPeopleExcludeBlocks() {
    return personRepository.findByBlockIsNotNull();
  }

  @Transactional(readOnly = true)
  public Person getPerson(Long id) {
    Person person = personRepository.findById(id).get();

    log.info("person : {}", person);

    return person;
  }

  public List<Person> getPeopleByName(String name) {
    return personRepository.findByName(name);
  }
}
