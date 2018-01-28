package com.nazareth.currency.converter.controllers;

import com.nazareth.currency.converter.controllers.jsons.ErrorResponse;
import com.nazareth.currency.converter.exceptions.CurrencyNotFoundException;
import com.nazareth.currency.converter.exceptions.InvalidDateException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@ControllerAdvice
public class CustomExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

  public static final String DEFAULT_ERROR_VIEW = "/error/error";

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(CurrencyNotFoundException.class)
  public ErrorResponse handleCurrencyNotFoundException(Exception e) {
    logger.error("{}", e);
    return new ErrorResponse(
        ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString(), e);
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(InvalidDateException.class)
  public ModelAndView handleTemplateProcessingException(HttpServletRequest req, Exception e) {
    logger.error("{}", e);

    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", e);
    mav.addObject("url", req.getRequestURL());
    mav.setViewName(DEFAULT_ERROR_VIEW);
    return mav;
  }

  @ExceptionHandler(value = Exception.class)
  public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
    // If the exception is annotated with @ResponseStatus rethrow it and let
    // the framework handle it - like the OrderNotFoundException example
    // at the start of this post.
    // AnnotationUtils is a Spring Framework utility class.
    if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) throw e;

    // Otherwise setup and send the user to a default error-view.
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", e);
    mav.addObject("url", req.getRequestURL());
    mav.setViewName(DEFAULT_ERROR_VIEW);
    return mav;
  }
}
