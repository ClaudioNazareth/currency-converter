package com.nazareth.currency.converter.controllers;

import static com.nazareth.currency.converter.fixture.CurrenciesTemplateLoader.VALID_CURRENCY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.nazareth.currency.converter.domains.mongo.Currencies;
import com.nazareth.currency.converter.fixture.SpringSecurityMock;
import com.nazareth.currency.converter.usecases.GetCurrencies;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

@RunWith(MockitoJUnitRunner.class)
public class DefaultControllerTest {

  @InjectMocks private DefaultController defaultController;

  @Mock private GetCurrencies getCurrencies;

  @Before
  public void setUp() throws Exception {
    SpringSecurityMock.mockUser();
    FixtureFactoryLoader.loadTemplates("com.nazareth.currency.converter.fixture");
  }

  public void get_currencies_could_not_be_null() {
    Throwable thrown = catchThrowable(() -> new CurrenciesController(null));
    assertThat(thrown).isExactlyInstanceOf(IllegalArgumentException.class);
    assertThat(thrown).hasMessageContaining("GetCurrencies is required");
  }

  @Test
  public void root() {
    Set<Currencies> currencies = new HashSet<>();
    currencies.add(Fixture.from(Currencies.class).gimme(VALID_CURRENCY));
    currencies.add(Fixture.from(Currencies.class).gimme(VALID_CURRENCY));

    when(getCurrencies.findTop10ByUsername(anyString())).thenReturn(currencies);

    Model model = new BindingAwareModelMap();
    final String index = defaultController.root(model);
    assertThat(index).as("The return should be index").isEqualTo("index");
  }

  @Test
  public void login() {
    Model model = new BindingAwareModelMap();
    final String login = defaultController.login(model);
    assertThat(login).as("The return should be login").isEqualTo("login");
  }

  @Test
  public void userIndex() {
    final String userIndex = defaultController.userIndex();
    assertThat(userIndex).as("The return should be user/index").isEqualTo("user/index");
  }

  @Test
  public void error403() {
    final String error403 = defaultController.error403();
    assertThat(error403).as("The return should be /error/403").isEqualTo("/error/403");
  }
}
