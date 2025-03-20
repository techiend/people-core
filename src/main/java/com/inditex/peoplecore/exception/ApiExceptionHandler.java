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
   * Handle not found price {@link PriceNotFoundException}.
   * @param ex expection.
   * @return {@link ResponseEntity} with {@link StandardizedApiExceptionResponse} body.
   */
  @ExceptionHandler(PriceNotFoundException.class)
  public ResponseEntity<StandardizedApiExceptionResponse> handlePriceNotFoundException(PriceNotFoundException ex) {
    StandardizedApiExceptionResponse standardizedApiExceptionResponse =
        new StandardizedApiExceptionResponse("BUSINESS", "Business Error", ex.getCode(), ex.getMessage());
    return ResponseEntity.status(ex.getHttpStatus()).body(standardizedApiExceptionResponse);
  }

  /**
   * Handle generic {@link Exception}.
   * @param ex generic expection.
   * @return {@link ResponseEntity} with {@link StandardizedApiExceptionResponse} body.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<StandardizedApiExceptionResponse> handleException(Exception ex) {
    StandardizedApiExceptionResponse standardizedApiExceptionResponse =
        new StandardizedApiExceptionResponse("GENERAL", "General Error", "000", ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardizedApiExceptionResponse);
  }

}
