package com.nazareth.currency.converter.usecases;

import com.nazareth.currency.converter.domains.mongo.Currencies;
import com.nazareth.currency.converter.exceptions.InvalidDateException;
import com.nazareth.currency.converter.gateways.CurrenciesGateway;
import com.nazareth.currency.converter.gateways.CurrenciesHistoryGateway;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCurrencies {

  private CurrenciesGateway currenciesGateway;

  private CurrenciesHistoryGateway currenciesHistoryGateway;

  @Autowired
  public GetCurrencies(
      CurrenciesGateway currenciesGateway, CurrenciesHistoryGateway currenciesHistoryGateway) {
    this.currenciesGateway = currenciesGateway;
    this.currenciesHistoryGateway = currenciesHistoryGateway;
  }

  public Currencies getCurrencies(String date) {

    if ((date != null && !date.isEmpty()) && !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
      new InvalidDateException(date);
    }

    Currencies currencies;

    if (date == null || date.isEmpty()) {
      currencies = currenciesGateway.getCurrencies();
    } else {
      currencies = currenciesGateway.getHistoricalCurrencies(date);
    }

    currenciesHistoryGateway.save(currencies);

    return currencies;
  }

  public Set<Currencies> findTop10ByUsername(String username) {
    return currenciesHistoryGateway.findTop10ByUserUser(username);
  }
}
