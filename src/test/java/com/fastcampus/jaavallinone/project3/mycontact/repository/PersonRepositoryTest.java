package com.fastcampus.jaavallinone.project3.mycontact.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.fastcampus.jaavallinone.project3.mycontact.domain.Person;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class PersonRepositoryTest {

  @Autowired
  private PersonRepository personRepository;

  @Test
  void crud() {

    Person person = new Person();
    person.setName("john");

    personRepository.save(person);

    List<Person> result = personRepository.findByName("john");

    assertThat(result.size()).isEqualTo(1);
    assertThat(result.get(0).getName()).isEqualTo("john");
//    assertThat(result.get(0).getAge()).isEqualTo(10);
  }

  @Test
  void findByBirthdayBetween() {
    List<Person> result = personRepository.findByMonthOfBirthday(8);

    assertThat(result.size()).isEqualTo(2);
    assertThat(result.get(0).getName()).isEqualTo("martin");
    assertThat(result.get(1).getName()).isEqualTo("sophia");
  }
}