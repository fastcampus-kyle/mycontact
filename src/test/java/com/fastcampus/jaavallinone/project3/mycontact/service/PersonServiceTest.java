package com.fastcampus.jaavallinone.project3.mycontact.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fastcampus.jaavallinone.project3.mycontact.controller.dto.PersonDto;
import com.fastcampus.jaavallinone.project3.mycontact.domain.Person;
import com.fastcampus.jaavallinone.project3.mycontact.repository.PersonRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

  @InjectMocks
  private PersonService personService;

  @Mock
  private PersonRepository personRepository;

  @Test
  void getPepopleByName() {
    when(personRepository.findByName("martin"))
        .thenReturn(Lists.newArrayList(new Person("martin")));

    List<Person> result = personService.getPeopleByName("martin");

    assertThat(result.size()).isEqualTo(1);
    assertThat(result.get(0).getName()).isEqualTo("martin");
  }

  @Test
  void getPerson() {
    when(personRepository.findById(1L))
        .thenReturn(Optional.of(new Person("martin")));

    Person person = personService.getPerson(1L);

    assertThat(person.getName()).isEqualTo("martin");
  }

  @Test
  void getPersonifNotFount() {
    when(personRepository.findById(1L))
        .thenReturn(Optional.empty());

    Person person = personService.getPerson(1L);

    assertThat(person).isNull();
  }

  @Test
  void put() {
    PersonDto dto = PersonDto
        .of("martin", "programming", "판교", LocalDate.now(), "programmer", "010-1111-2222");

    personService.put(dto);

    verify(personRepository, times(1)).save(any(Person.class));
  }
}