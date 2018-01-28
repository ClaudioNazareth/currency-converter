package com.nazareth.currency.converter.usecases;

import static com.nazareth.currency.converter.fixture.UserTemplateLoader.USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.nazareth.currency.converter.domains.jpa.User;
import com.nazareth.currency.converter.gateways.UserGateway;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticateUserTest {

  @InjectMocks private AuthenticateUser authenticateUser;

  @Mock private UserGateway userGateway;

  @Before
  public void setUp() throws Exception {
    FixtureFactoryLoader.loadTemplates("com.nazareth.currency.converter.fixture");
  }

  @Test
  public void dependencies_should_not_be_null() {
    Throwable thrown = catchThrowable(() -> new AuthenticateUser(null));
    assertThat(thrown).isExactlyInstanceOf(IllegalArgumentException.class);
    assertThat(thrown).hasMessageContaining("UserGateway is required");
  }

  @Test
  public void should_return_the_user_when_it_exists() {
    String username = "chtnzareth@gmail.com";

    when(userGateway.findUserByUsername(username))
        .thenReturn(Optional.of(Fixture.from(User.class).gimme(USER)));

    final UserDetails userDetails = authenticateUser.loadUserByUsername(username);
    assertThat(userDetails).isNotNull();
  }

  @Test
  public void should_throw_UsernameNotFoundException_when_user_not_exists() {
    String username = "chtnzareth@gmail.com";
    when(userGateway.findUserByUsername(username)).thenReturn(Optional.empty());
    Throwable thrown = catchThrowable(() -> authenticateUser.loadUserByUsername(username));
    assertThat(thrown).isExactlyInstanceOf(UsernameNotFoundException.class);
    assertThat(thrown).hasMessageContaining("Invalid username or password");
  }
}
