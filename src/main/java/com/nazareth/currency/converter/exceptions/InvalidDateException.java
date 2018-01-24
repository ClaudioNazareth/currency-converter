package com.nazareth.currency.converter.exceptions;

public class InvalidDateException extends RuntimeException {

  public InvalidDateException(String date) {
    super(String.format("Date : %s was not in correct format : yyyy-MM-dd", date));
  }
}
