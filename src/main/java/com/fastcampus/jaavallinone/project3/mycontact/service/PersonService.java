package com.fastcampus.jaavallinone.project3.mycontact.service;

import com.fastcampus.jaavallinone.project3.mycontact.controller.dto.PersonDto;
import com.fastcampus.jaavallinone.project3.mycontact.domain.Person;
import com.fastcampus.jaavallinone.project3.mycontact.exception.PersonNotFoundException;
import com.fastcampus.jaavallinone.project3.mycontact.exception.RenameNotPermiitedException;
import com.fastcampus.jaavallinone.project3.mycontact.repository.PersonRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class PersonService {

  @Autowired
  private PersonRepository personRepository;

  @Transactional(readOnly = true)
  public Person getPerson(Long id) {
    return personRepository.findById(id).orElse(null);
  }

  public List<Person> getPeopleByName(String name) {
    return personRepository.findByName(name);
  }

  @Transactional
  public void put(PersonDto personDto) {
    Person person = new Person();
    person.set(personDto);
    person.setName(personDto.getName());
    personRepository.save(person);
  }

  @Transactional
  public void modify(Long id, PersonDto personDto) {
    Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);

    if (!person.getName().equals(personDto.getName())) {
      throw new RenameNotPermiitedException();
    }

    person.set(personDto);

    personRepository.save(person);
  }

  @Transactional
  public void modify(Long id, String name) {
    Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);

    person.setName(name);

    personRepository.save(person);
  }

  @Transactional
  public void delete(Long id) {
    Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);

    person.setDeleted(true);
    personRepository.save(person);
  }

  public Page<Person> getAll(Pageable pageable) {
    return personRepository.findAll(pageable);
  }
}
