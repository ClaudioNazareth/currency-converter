package com.nazareth.currency.converter.gateways;

import static com.nazareth.currency.converter.fixture.CurrenciesResponseTemplateLoader.VALID_CURRENCY_RESPONSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.nazareth.currency.converter.domains.mongo.Currencies;
import com.nazareth.currency.converter.fixture.SpringSecurityMock;
import com.nazareth.currency.converter.gateways.http.CurrenciesGatewayImpl;
import com.nazareth.currency.converter.gateways.http.feign.FeignCurrencyClient;
import com.nazareth.currency.converter.gateways.http.feign.jsons.CurrenciesResponse;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurrenciesGatewayTest {

  @InjectMocks private CurrenciesGatewayImpl currenciesGateway;

  @Mock private FeignCurrencyClient feignCurrencyClient;

  @Before
  public void setUp() throws Exception {
    SpringSecurityMock.mockUser();
    FixtureFactoryLoader.loadTemplates("com.nazareth.currency.converter.fixture");
  }

  @Test
  public void should_return_currencies() {
    Mockito.when(feignCurrencyClient.getCurrencies())
        .thenReturn(Fixture.from(CurrenciesResponse.class).gimme(VALID_CURRENCY_RESPONSE));

    final Currencies currencies = currenciesGateway.getCurrencies();
    assertThat(currencies).isNotNull();
    assertThat(currencies.getRates()).isNotNull();
    assertThat(currencies.getCurrencyBase()).isEqualTo("USD");
    assertThat(currencies.getCurrencyDate()).isInstanceOf(LocalDateTime.class);
  }

  @Test
  public void should_return_historical_currencies() {

    Mockito.when(feignCurrencyClient.getHistoricalCurrencies(anyString()))
        .thenReturn(Fixture.from(CurrenciesResponse.class).gimme(VALID_CURRENCY_RESPONSE));

    final Currencies currencies = currenciesGateway.getHistoricalCurrencies("2017-01-24");
    assertThat(currencies).isNotNull();
    assertThat(currencies.getRates()).isNotNull();
    assertThat(currencies.getCurrencyBase()).isEqualTo("USD");
    assertThat(currencies.getCurrencyDate()).isInstanceOf(LocalDateTime.class);
  }
}
