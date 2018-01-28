package com.nazareth.currency.converter.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.nazareth.currency.converter.controllers.jsons.ErrorResponse;
import com.nazareth.currency.converter.exceptions.InvalidDateException;
import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RestApiExceptionHandlerTest {

  private RestApiExceptionHandler restApiExceptionHandler = new RestApiExceptionHandler();

  @Before
  public void setUp() {
    HttpServletRequest mockRequest = new MockHttpServletRequest();
    ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
    RequestContextHolder.setRequestAttributes(servletRequestAttributes);
  }

  @Test
  public void handleUserInBlackListException() {
    ErrorResponse errorResponse =
        restApiExceptionHandler.handleInvalidDateException(new InvalidDateException("2012-12"));
    assertThat(errorResponse.getEx())
        .contains("Date : 2012-12 was not in correct format : yyyy-MM-dd");
  }
}
