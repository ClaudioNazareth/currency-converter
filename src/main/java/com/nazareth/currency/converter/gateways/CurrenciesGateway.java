package com.nazareth.currency.converter.gateways;

import com.nazareth.currency.converter.domains.mongo.Currencies;

public interface CurrenciesGateway {

  Currencies getCurrencies();

  Currencies getHistoricalCurrencies(String date);
}
