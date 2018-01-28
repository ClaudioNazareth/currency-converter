package com.nazareth.currency.converter.controllers;

import static com.nazareth.currency.converter.fixture.CurrenciesTemplateLoader.VALID_CURRENCY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.nazareth.currency.converter.controllers.jsons.CurrenciesApiResponse;
import com.nazareth.currency.converter.domains.mongo.Currencies;
import com.nazareth.currency.converter.fixture.SpringSecurityMock;
import com.nazareth.currency.converter.usecases.GetCurrencies;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyConverterRestControllerTest {

  @InjectMocks private CurrencyConverterRestController currencyConverterRestController;

  @Mock private GetCurrencies getCurrencies;

  @Before
  public void setUp() throws Exception {
    SpringSecurityMock.mockUser();
    FixtureFactoryLoader.loadTemplates("com.nazareth.currency.converter.fixture");
  }

  @Test
  public void should_return_currency() {
    when(getCurrencies.getCurrencies(null, null))
        .thenReturn(Fixture.from(Currencies.class).gimme(VALID_CURRENCY));

    final CurrenciesApiResponse currencies = currencyConverterRestController.getCurrencies(null);

    verify(getCurrencies, times(1)).getCurrencies(null, null);
    assertThat(currencies).isNotNull();
    assertThat(currencies.getCurrencyDate()).isNotNull().isExactlyInstanceOf(LocalDateTime.class);
    assertThat(currencies.getCurrencyBase()).isNotNull().isEqualTo("USD");
    assertThat(currencies.getRates()).isNotNull();
  }

  @Test
  public void should_return_ten_last_currencies_query() {

    Set<Currencies> currencies = new HashSet<>();
    currencies.add(Fixture.from(Currencies.class).gimme(VALID_CURRENCY));
    currencies.add(Fixture.from(Currencies.class).gimme(VALID_CURRENCY));

    when(getCurrencies.findTop10ByUsername(anyString())).thenReturn(currencies);

    final List<CurrenciesApiResponse> top10CurrenciesHistory =
        currencyConverterRestController.getTop10CurrenciesHistory();

    verify(getCurrencies, times(1)).findTop10ByUsername(anyString());

    assertThat(top10CurrenciesHistory).isNotNull().hasSize(2);
    assertThat(top10CurrenciesHistory.get(0).getCurrencyBase()).isEqualTo("USD");
    assertThat(top10CurrenciesHistory.get(0).getCurrencyDate())
        .isNotNull()
        .isExactlyInstanceOf(LocalDateTime.class);

    assertThat(top10CurrenciesHistory.get(0).getRates()).isNotNull();
  }
}
