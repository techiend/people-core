package com.inditex.peoplecore.exception;

import com.inditex.peoplecore.common.StandardizedApiExceptionResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Api exception handler.
 */
@Hidden
@RestControllerAdvice
public class ApiExceptionHandler {

  /**
   * Handle generic {@link Exception}.
   * @param ex generic expection.
   * @return {@link ResponseEntity} with {@link StandardizedApiExceptionResponse} body.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleException(Exception ex) {
    StandardizedApiExceptionResponse standardizedApiExceptionResponse =
        new StandardizedApiExceptionResponse("GENERAL", "General Error", "000", ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardizedApiExceptionResponse);
  }

}
