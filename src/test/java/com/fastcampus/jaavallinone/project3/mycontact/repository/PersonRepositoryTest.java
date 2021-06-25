package com.fastcampus.jaavallinone.project3.mycontact.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.fastcampus.jaavallinone.project3.mycontact.domain.Person;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonRepositoryTest {

  @Autowired
  private PersonRepository personRepository;
  @Test
  void crud(){

    Person person = new Person();
    person.setName("martin");
    person.setAge(10);

    personRepository.save(person);

//    System.out.println(personRepository.findAll());

    List<Person> people = personRepository.findAll();

    assertThat(people.size()).isEqualTo(1);
    assertThat(people.get(0).getName()).isEqualTo("martin");
    assertThat(people.get(0).getAge()).isEqualTo(10);

  }
}