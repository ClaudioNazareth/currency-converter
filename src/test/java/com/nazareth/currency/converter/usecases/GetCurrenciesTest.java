package com.nazareth.currency.converter.usecases;

import static com.nazareth.currency.converter.fixture.CurrenciesTemplateLoader.VALID_CURRENCY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.nazareth.currency.converter.domains.mongo.Currencies;
import com.nazareth.currency.converter.exceptions.InvalidDateException;
import com.nazareth.currency.converter.gateways.CurrenciesGateway;
import com.nazareth.currency.converter.gateways.CurrenciesHistoryGateway;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetCurrenciesTest {

  @InjectMocks private GetCurrencies getCurrencies;

  @Mock private CurrenciesGateway currenciesGateway;

  @Mock private CurrenciesHistoryGateway currenciesHistoryGateway;

  @Before
  public void setUp() throws Exception {
    FixtureFactoryLoader.loadTemplates("com.nazareth.currency.converter.fixture");
  }

  @Test
  public void dependencies_should_not_be_null() {
    Throwable thrown = catchThrowable(() -> new GetCurrencies(null, currenciesHistoryGateway));
    assertThat(thrown).isExactlyInstanceOf(IllegalArgumentException.class);
    assertThat(thrown).hasMessageContaining("CurrenciesGateway is required");

    thrown = catchThrowable(() -> new GetCurrencies(currenciesGateway, null));
    assertThat(thrown).isExactlyInstanceOf(IllegalArgumentException.class);
    assertThat(thrown).hasMessageContaining("CurrenciesHistoryGateway is required");
  }

  @Test
  public void should_save_and_return_currencies_when_date_is_correct() {

    String date = "2017-05-21";

    when(currenciesGateway.getHistoricalCurrencies(date))
        .thenReturn(Fixture.from(Currencies.class).gimme(VALID_CURRENCY));

    final Currencies currencies = getCurrencies.getCurrencies(date, "chtnazareth@gmaial.com");
    assertThat(currencies).isNotNull();
    assertThat(currencies.getCurrencyDate()).isNotNull().isInstanceOf(LocalDateTime.class);
    assertThat(currencies.getCurrencyBase()).isEqualTo("USD");
  }

  @Test
  public void should_save_and_return_currencies_when_date_is_null() {
    when(currenciesGateway.getCurrencies())
        .thenReturn(Fixture.from(Currencies.class).gimme(VALID_CURRENCY));

    final Currencies currencies = getCurrencies.getCurrencies(null, "chtnazareth@gmail.com");

    assertThat(currencies).isNotNull();
    assertThat(currencies.getCurrencyDate()).isNotNull().isInstanceOf(LocalDateTime.class);
    assertThat(currencies.getCurrencyBase()).isEqualTo("USD");
  }

  @Test
  public void should_return_InvalidDateException_when_date_is_not_valid() {

    when(currenciesGateway.getCurrencies())
        .thenReturn(Fixture.from(Currencies.class).gimme(VALID_CURRENCY));

    Throwable thrown =
        catchThrowable(() -> getCurrencies.getCurrencies("21-1788", "chtnazareth@gmail.com"));
    assertThat(thrown).isExactlyInstanceOf(InvalidDateException.class);
    assertThat(thrown).hasMessageContaining("21-1788 was not in correct format : yyyy-MM-dd");
  }

  @Test
  public void should_return_top_ten_by_user_name() {

    Set<Currencies> currencies = new HashSet<>();
    currencies.add(Fixture.from(Currencies.class).gimme(VALID_CURRENCY));
    currencies.add(Fixture.from(Currencies.class).gimme(VALID_CURRENCY));

    when(currenciesHistoryGateway.findTop10ByUserUser(anyString())).thenReturn(currencies);

    final Set<Currencies> top10ByUsername =
        getCurrencies.findTop10ByUsername("chtnazareth@gmail.com");

    assertThat(top10ByUsername).isNotEmpty().isNotNull().hasSize(2);
  }
}
