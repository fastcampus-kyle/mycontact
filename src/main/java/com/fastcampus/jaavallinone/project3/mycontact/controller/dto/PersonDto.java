package com.fastcampus.jaavallinone.project3.mycontact.controller.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PersonDto {

  private String name;
  private int age;
  private String hobby;
  private String bloodType;
  private String address;
  private LocalDate birthday;
  private String job;
  private String phoneNumber;
}
