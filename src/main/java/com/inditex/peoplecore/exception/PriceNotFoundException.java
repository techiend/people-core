package com.inditex.peoplecore.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Exception for handle price not found.
 */
@Data
public class PriceNotFoundException extends Exception {

  /**
   * Exception code on business (if exist).
   */
  private final String code;

  /**
   * HttpStatus code response for Exception Handler.
   */
  private final HttpStatus httpStatus;

  /**
   * Basic constructor.
   *
   * @param code error code on business
   * @param message error description
   * @param httpStatus http status for response
   */
  public PriceNotFoundException(String code, String message, HttpStatus httpStatus) {
    super(message);
    this.code = code;
    this.httpStatus = httpStatus;
  }

}
