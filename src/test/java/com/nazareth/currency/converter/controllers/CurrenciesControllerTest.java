package com.nazareth.currency.converter.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.nazareth.currency.converter.fixture.SpringSecurityMock;
import com.nazareth.currency.converter.usecases.GetCurrencies;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

@RunWith(MockitoJUnitRunner.class)
public class CurrenciesControllerTest {

  @InjectMocks private CurrenciesController currenciesController;

  @Mock private GetCurrencies getCurrencies;

  @Before
  public void setUp() throws Exception {
    SpringSecurityMock.mockUser();
  }

  @Test
  public void get_currencies_could_not_be_null() {
    Throwable thrown = catchThrowable(() -> new CurrenciesController(null));
    assertThat(thrown).isExactlyInstanceOf(IllegalArgumentException.class);
    assertThat(thrown).hasMessageContaining("GetCurrencies is required");
  }

  @Test
  public void should_set_the_model_values_and_return_index() {

    Model model = new BindingAwareModelMap();

    final String indexPage = currenciesController.getCurrencies(null, model);

    assertThat(indexPage).as("The return should be index").isEqualTo("index");

    verify(getCurrencies, times(1)).getCurrencies(null, null);
  }
}
