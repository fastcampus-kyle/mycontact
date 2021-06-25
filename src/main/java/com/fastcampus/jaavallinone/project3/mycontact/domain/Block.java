package com.fastcampus.jaavallinone.project3.mycontact.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Block {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String reason;

  private LocalDate startDate;

  private LocalDate endDate;

}
