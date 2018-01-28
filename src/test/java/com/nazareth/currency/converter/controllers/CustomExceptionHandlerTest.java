package com.nazareth.currency.converter.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionHandlerTest {

  private CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();

  HttpServletRequest mockRequest;

  @Before
  public void setUp() {
    mockRequest = new MockHttpServletRequest();
    ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
    RequestContextHolder.setRequestAttributes(servletRequestAttributes);
  }

  @Test
  public void defaultErrorHandler() throws Exception {
    final ModelAndView modelAndView =
        customExceptionHandler.defaultErrorHandler(mockRequest, new RuntimeException());

    assertThat(modelAndView).isNotNull();
    assertThat(modelAndView.getViewName()).isEqualTo("/error/error");
  }
}
