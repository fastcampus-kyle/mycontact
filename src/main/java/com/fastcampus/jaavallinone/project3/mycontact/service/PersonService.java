package com.fastcampus.jaavallinone.project3.mycontact.service;

import com.fastcampus.jaavallinone.project3.mycontact.domain.Person;
import com.fastcampus.jaavallinone.project3.mycontact.repository.PersonRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  @Autowired
  private PersonRepository personRepository;

  public List<Person> getPeopleExcludeBlocks() {
    List<Person> people = personRepository.findAll();

    return people.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList());

  }
}
