package com.inditex.peoplecore.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StandardizedApiExceptionResponse {

  private String type;
  private String title;
  private String code;
  private String detail;
  private String instance;

  public StandardizedApiExceptionResponse(String type, String title, String code, String detail) {
    super();
    this.type = type;
    this.title = title;
    this.code = code;
    this.detail = detail;
  }
}
