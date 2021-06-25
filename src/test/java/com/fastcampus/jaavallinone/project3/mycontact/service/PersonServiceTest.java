package com.fastcampus.jaavallinone.project3.mycontact.service;

import static org.junit.jupiter.api.Assertions.*;

import com.fastcampus.jaavallinone.project3.mycontact.domain.Block;
import com.fastcampus.jaavallinone.project3.mycontact.domain.Person;
import com.fastcampus.jaavallinone.project3.mycontact.repository.BlockRepository;
import com.fastcampus.jaavallinone.project3.mycontact.repository.PersonRepository;
import java.util.List;
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
    givenPeople();
    givenBlocks();

    List<Person> result = personService.getPeopleExcludeBlocks();

//    System.out.println(result);
    result.forEach(System.out::println);
  }

  private void givenBlocks() {
    givenBlock("martin");
  }

  private Block givenBlock(String name) {
    return blockRepository.save(new Block(name));
  }

  private void givenPeople() {
    givenPerson("martin", 10, "A");
    givenPerson("david", 9, "B");
    givenBlockPerson("dennis", 7, "O");
    givenBlockPerson("martin", 11, "AB");
  }

  private void givenPerson(String name, int age, String bloodType) {
    personRepository.save(new Person(name, age, bloodType));
  }

  private void givenBlockPerson(String name, int age, String bloodType) {
    Person blockPerson = new Person(name, age, bloodType);
    blockPerson.setBlock(givenBlock(name));
    personRepository.save(blockPerson);

  }

}