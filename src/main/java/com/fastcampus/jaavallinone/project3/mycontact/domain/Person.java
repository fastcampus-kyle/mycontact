package com.fastcampus.jaavallinone.project3.mycontact.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Person {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private int age;

  private String hobby;

  private String bloodType;

  private String address;

  private LocalDate birthday;

  private String job;

  @ToString.Exclude
  private String phoneNumber;
}