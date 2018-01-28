package com.nazareth.currency.converter.usecases;

import com.nazareth.currency.converter.domains.mongo.Currencies;
import com.nazareth.currency.converter.exceptions.InvalidDateException;
import com.nazareth.currency.converter.gateways.CurrenciesGateway;
import com.nazareth.currency.converter.gateways.CurrenciesHistoryGateway;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class GetCurrencies {

  public static final String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";

  private CurrenciesGateway currenciesGateway;

  private CurrenciesHistoryGateway currenciesHistoryGateway;

  @Autowired
  public GetCurrencies(
      CurrenciesGateway currenciesGateway, CurrenciesHistoryGateway currenciesHistoryGateway) {
    Assert.notNull(currenciesGateway, "CurrenciesGateway is required");
    Assert.notNull(currenciesHistoryGateway, "CurrenciesHistoryGateway is required");
    this.currenciesGateway = currenciesGateway;
    this.currenciesHistoryGateway = currenciesHistoryGateway;
  }

  public Currencies getCurrencies(String date, String username) {

    if ((date != null && !date.isEmpty()) && !date.matches(DATE_REGEX)) {
      throw new InvalidDateException(date);
    }

    Currencies currencies;

    if (date == null || date.isEmpty()) {
      currencies = currenciesGateway.getCurrencies();
    } else {
      currencies = currenciesGateway.getHistoricalCurrencies(date);
    }

    currenciesHistoryGateway.save(currencies, username);

    return currencies;
  }

  public Set<Currencies> findTop10ByUsername(String username) {
    return currenciesHistoryGateway.findTop10ByUserUser(username);
  }
}
