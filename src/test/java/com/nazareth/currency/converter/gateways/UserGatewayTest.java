package com.nazareth.currency.converter.gateways;

import static com.nazareth.currency.converter.fixture.UserTemplateLoader.USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.nazareth.currency.converter.domains.jpa.User;
import com.nazareth.currency.converter.gateways.h2.UserGatewayImpl;
import com.nazareth.currency.converter.gateways.h2.repositories.UserRepository;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserGatewayTest {

  @InjectMocks private UserGatewayImpl userGateway;

  @Mock private UserRepository userRepository;

  @Before
  public void setUp() throws Exception {
    FixtureFactoryLoader.loadTemplates("com.nazareth.currency.converter.fixture");
  }

  @Test
  public void dependencies_should_not_be_null() {
    Throwable thrown = catchThrowable(() -> new UserGatewayImpl(null));
    assertThat(thrown).isExactlyInstanceOf(IllegalArgumentException.class);
    assertThat(thrown).hasMessageContaining("UserRepository is required");
  }

  @Test
  public void should_save_user() {
    User user = Fixture.from(User.class).gimme(USER);
    when(userRepository.save(user)).thenReturn(user);

    final User savedUser = userGateway.save(user);
    verify(userRepository, times(1)).save(user);
    assertThat(savedUser).isNotNull();
  }

  @Test
  public void should_return_user() {
    String username = "chtnazareth@gmail.com";
    User user = Fixture.from(User.class).gimme(USER);
    when(userRepository.findByEmail(username)).thenReturn(Optional.of(user));

    final Optional<User> userByUsername = userGateway.findUserByUsername(username);
    verify(userRepository, times(1)).findByEmail(username);
    assertThat(userByUsername.isPresent()).isTrue();
  }
}
