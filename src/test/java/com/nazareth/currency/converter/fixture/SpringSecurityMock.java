package com.nazareth.currency.converter.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nazareth.currency.converter.domains.jpa.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Mock class for spring security
 *
 * @author Claudio Nazareth
 */
public class SpringSecurityMock {

  public static void mockUser() {
    User applicationUser = new User("Claudio", "Nazareth", "chtnazareth@gmail.com", "1qazxSW@");
    Authentication authentication = mock(Authentication.class);

    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    SecurityContextHolder.setContext(securityContext);
    when(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        .thenReturn(applicationUser);
  }
}
