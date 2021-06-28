package com.fastcampus.jaavallinone.project3.mycontact.controller.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PersonDto {

  private String name;
  private String hobby;
  private String address;
  private LocalDate birthday;
  private String job;
  private String phoneNumber;
}
