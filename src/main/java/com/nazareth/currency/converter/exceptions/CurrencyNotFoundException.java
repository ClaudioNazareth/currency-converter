package com.nazareth.currency.converter.exceptions;

/**
 * Exception to be throw when currency is no found
 *
 * @author Claudio Nazareth
 */
public class CurrencyNotFoundException extends RuntimeException {

  public CurrencyNotFoundException(String currency) {
    super(String.format("Currency : %s was not found", currency));
  }
}
