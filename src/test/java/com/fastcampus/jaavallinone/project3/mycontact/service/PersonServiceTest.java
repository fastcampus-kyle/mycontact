package com.fastcampus.jaavallinone.project3.mycontact.service;

import com.fastcampus.jaavallinone.project3.mycontact.domain.Block;
import com.fastcampus.jaavallinone.project3.mycontact.domain.Person;
import com.fastcampus.jaavallinone.project3.mycontact.repository.BlockRepository;
import com.fastcampus.jaavallinone.project3.mycontact.repository.PersonRepository;
import java.time.LocalDate;
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

    List<Person> result = personService.getPeopleExcludeBlocks();

    result.forEach(System.out::println);
  }

  @Test
  void getPepopleByName(){
    givenPeople();

    List<Person> result = personService.getPeopleByName("martin");

    result.forEach(System.out::println);
  }

  @Test
  void getPerson(){
    givenPeople();

    Person person = personService.getPerson(3L);

    System.out.println(person);
  }

  @Test
  void cascadeTest() {
    givenPeople();

    List<Person> result = personRepository.findAll();
    result.forEach(System.out::println);

    Person person = result.get(3);
    person.getBlock().setStartDate(LocalDate.now());
    person.getBlock().setEndDate(LocalDate.now());

    personRepository.save(person);
    personRepository.findAll().forEach(System.out::println);

//    personRepository.delete(person);
//    personRepository.findAll().forEach(System.out::println);
//    blockRepository.findAll().forEach(System.out::println);

    person.setBloodType(null);
    personRepository.save(person);
    personRepository.findAll().forEach(System.out::println);
    blockRepository.findAll().forEach(System.out::println);
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
    blockPerson.setBlock(new Block(name));
    personRepository.save(blockPerson);

  }

}