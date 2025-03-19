package com.inditex.peoplecore.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Standard API Exception object.
 */
@NoArgsConstructor
@Data
public class StandardizedApiExceptionResponse {

  /**
   * Exception type.
   */
  private String type;

  /**
   * Exception title.
   */
  private String title;

  /**
   * Exception code.
   */
  private String code;

  /**
   * Exception detail.
   */
  private String detail;

  /**
   * Exception instance.
   */
  private String instance;

  /**
   * Standard API Exception constructor.
   *
   * @param type exception type.
   * @param title exception title.
   * @param code exception code.
   * @param detail exception detail.
   */
  public StandardizedApiExceptionResponse(String type, String title, String code, String detail) {
    super();
    this.type = type;
    this.title = title;
    this.code = code;
    this.detail = detail;
  }
}
