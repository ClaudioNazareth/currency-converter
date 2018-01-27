package com.nazareth.currency.converter.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

import com.nazareth.currency.converter.domains.mongo.Currencies;
import com.nazareth.currency.converter.gateways.CurrenciesGateway;
import com.nazareth.currency.converter.gateways.CurrenciesHistoryGateway;
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
    final Currencies currencies = getCurrencies.getCurrencies("2017-05-21");
    assertThat(currencies).isNotNull();
  }
}
