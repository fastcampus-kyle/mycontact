package com.fastcampus.jaavallinone.project3.mycontact.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {

  @Id
  @GeneratedValue
  private Long id;

  @NonNull
  private String name;

  @NonNull
  private int age;

  private String hobby;

  @NonNull
  private String bloodType;

  private String address;

  private LocalDate birthday;

  private String job;

  @ToString.Exclude
  private String phoneNumber;

  @OneToOne
  private Block block;
}