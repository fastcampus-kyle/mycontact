package com.fastcampus.jaavallinone.project3.mycontact.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RenameNotPermiitedException extends RuntimeException{
  private static final String MESSAGE = "이름을 변경 허용하지 않습니다.";

  public RenameNotPermiitedException(){
    super(MESSAGE);
    log.error(MESSAGE);
  }

}
