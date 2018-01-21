package com.nazareth.currency.converter.usecases;

import com.nazareth.currency.converter.domains.CurrencyConversion;
import com.nazareth.currency.converter.domains.User;
import com.nazareth.currency.converter.gateways.CurrenciesGateway;
import com.nazareth.currency.converter.gateways.http.feign.jsons.CurrenciesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertCurrency {

  private CurrenciesGateway currenciesGateway;

  private CrudCurrencyConversion crudCurrencyConversion;

  @Autowired
  public ConvertCurrency(
      CurrenciesGateway currenciesGateway, CrudCurrencyConversion crudCurrencyConversion) {
    this.currenciesGateway = currenciesGateway;
    this.crudCurrencyConversion = crudCurrencyConversion;
  }

  public CurrencyConversion convert(User user, Double amount, String from, String to) {

    final CurrenciesResponse currencies = currenciesGateway.getCurrencies();

    final Double fromCurrency = currencies.getRates().get(from);
    final Double toCurrency = currencies.getRates().get(to);
    final Double rate = fromCurrency / toCurrency;
    final Double result = amount * rate;

    final CurrencyConversion currencyConversion =
        crudCurrencyConversion.save(new CurrencyConversion(amount, from, to, rate, result, user));

    return currencyConversion;
  }
}
