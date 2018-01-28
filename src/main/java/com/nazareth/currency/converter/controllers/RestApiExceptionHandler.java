package com.nazareth.currency.converter.controllers;

import com.nazareth.currency.converter.controllers.jsons.ErrorResponse;
import com.nazareth.currency.converter.exceptions.InvalidDateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@ControllerAdvice(assignableTypes = CurrencyConverterRestController.class)
public class RestApiExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(InvalidDateException.class)
  public ErrorResponse handleInvalidDateException(Exception e) {
    logger.error("{}", e);
    return new ErrorResponse(
        ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString(), e);
  }
}
