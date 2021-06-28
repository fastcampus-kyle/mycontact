package com.fastcampus.jaavallinone.project3.mycontact.controller.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PersonDto {

  @NotBlank(message = "이름은 필수값입니다")
  private String name;
  private String hobby;
  private String address;
  private LocalDate birthday;
  private String job;
  private String phoneNumber;
}
