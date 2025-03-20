package com.inditex.peoplecore.exception;

/**
 * Exception code Business enumeration.
 */
public enum ExceptionCodeEnum {

  EMPTY_PRICES("001"),
  PRICE_NOT_FOUND("002");

  /**
   * Exception code value.
   */
  private final String value;

  ExceptionCodeEnum(String value) {
    this.value = value;
  }
}
