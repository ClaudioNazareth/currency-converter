package com.nazareth.currency.converter.exceptions;

/**
 * //TODO - Document
 *
 * @author Claudio Nazareth
 */
public class CurrencyNotFoundException extends RuntimeException {

  public CurrencyNotFoundException(String currency) {
    super(String.format("Currency : %s was not found", currency));
  }
}
