package com.fastcampus.jaavallinone.project3.mycontact.repository;

import com.fastcampus.jaavallinone.project3.mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
