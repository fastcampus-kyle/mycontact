package com.fastcampus.jaavallinone.project3.mycontact.repository;

import com.fastcampus.jaavallinone.project3.mycontact.domain.Person;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

  List<Person> findByName(String name);

  List<Person> findByBlockIsNotNull();

  List<Person> findByBloodType(String bloodType);

  List<Person> findByBirthdayBetween(LocalDate startDate, LocalDate endData);

}
