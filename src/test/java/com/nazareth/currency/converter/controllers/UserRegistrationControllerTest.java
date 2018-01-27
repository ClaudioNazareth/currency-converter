package com.nazareth.currency.converter.controllers;

import static com.nazareth.currency.converter.fixture.UserRequestTemplateLoader.USER_REQUEST;
import static com.nazareth.currency.converter.fixture.UserTemplateLoader.USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.nazareth.currency.converter.controllers.jsons.UserRequest;
import com.nazareth.currency.converter.domains.jpa.User;
import com.nazareth.currency.converter.usecases.CrudUser;
import java.util.HashMap;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.MapBindingResult;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationControllerTest {

  @InjectMocks private UserRegistrationController userRegistrationController;

  @Mock private CrudUser crudUser;

  @Before
  public void setUp() throws Exception {
    FixtureFactoryLoader.loadTemplates("com.nazareth.currency.converter.fixture");
  }

  @Test
  public void should_return_user_request() {
    assertThat(userRegistrationController.userRequest())
        .as("Should return a user request")
        .isNotNull()
        .isInstanceOf(UserRequest.class);
  }

  @Test
  public void should_redirect_to_registration_page() {
    assertThat(userRegistrationController.showRegistrationForm(null))
        .as("Should return registration")
        .isNotNull()
        .isEqualTo("registration");
  }

  @Test
  public void should_register_a_new_user() {

    when(crudUser.findByUserName(anyString())).thenReturn(Optional.empty());

    final String registrationSuccess =
        userRegistrationController.registerUserAccount(
            Fixture.from(UserRequest.class).gimme(USER_REQUEST),
            new MapBindingResult(new HashMap<>(), "user"));

    assertThat(registrationSuccess).isEqualTo("redirect:/registration?success");
  }

  @Test
  public void should_not_register_a_new_user_when_user_already_registred() {
    Optional<User> optionalUser = Optional.of(Fixture.from(User.class).gimme(USER));

    when(crudUser.findByUserName(anyString())).thenReturn(optionalUser);

    final MapBindingResult bindingResult = new MapBindingResult(new HashMap<>(), "user");

    userRegistrationController.registerUserAccount(
        Fixture.from(UserRequest.class).gimme(USER_REQUEST), bindingResult);

    assertThat(bindingResult.getErrorCount()).isEqualTo(1);

    assertThat(bindingResult.getAllErrors().get(0).getDefaultMessage())
        .isEqualTo("There is already an account registered with that  email");
  }
}
