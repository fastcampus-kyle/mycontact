package com.fastcampus.jaavallinone.project3.mycontact.domain;

import com.fastcampus.jaavallinone.project3.mycontact.domain.dto.Birthday;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @NotEmpty
  @Column(nullable = false)
  private String name;

  @NonNull
  @Min(1)
  private int age;

  private String hobby;

  @NotEmpty
  @NonNull
  @Column(nullable = false)
  private String bloodType;

  private String address;

  @Valid
  @Embedded
  private Birthday birthday;

  private String job;

  @ToString.Exclude
  private String phoneNumber;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private Block block;
}