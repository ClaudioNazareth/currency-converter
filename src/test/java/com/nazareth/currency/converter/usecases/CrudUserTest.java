package com.nazareth.currency.converter.usecases;

import static com.nazareth.currency.converter.fixture.UserTemplateLoader.USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

@RunWith(MockitoJUnitRunner.class)
public class CrudUserTest {

  @InjectMocks private CrudUser crudUser;

  @Mock private UserGateway userGateway;

  @Before
  public void setUp() throws Exception {
    FixtureFactoryLoader.loadTemplates("com.nazareth.currency.converter.fixture");
  }

  @Test
  public void dependencies_should_not_be_null() {
    Throwable thrown = catchThrowable(() -> new CrudUser(null));
    assertThat(thrown).isExactlyInstanceOf(IllegalArgumentException.class);
    assertThat(thrown).hasMessageContaining("UserGateway is required");
  }

  @Test
  public void should_find_user_when_it_exists() {
    String username = "chtnazareth@gmail.com";

    when(userGateway.findUserByUsername(username))
        .thenReturn(Optional.of(Fixture.from(User.class).gimme(USER)));

    final Optional<User> user = crudUser.findByUserName(username);
    assertThat(user.isPresent()).as("The object should be present").isTrue();

    assertThat(user.get()).as("The object should be an User").isInstanceOf(User.class);
  }

  @Test
  public void should_return_option_empty_user_when_not_exists() {
    String username = "chtnazareth@gmail.com";

    when(userGateway.findUserByUsername(username)).thenReturn(Optional.empty());

    final Optional<User> user = crudUser.findByUserName(username);
    assertThat(user.isPresent()).as("The object should not be present").isFalse();
  }

  @Test
  public void should_save_user() {
    User user = Fixture.from(User.class).gimme(USER);
    crudUser.save(user);
    verify(userGateway, times(1)).save(user);
  }
}
