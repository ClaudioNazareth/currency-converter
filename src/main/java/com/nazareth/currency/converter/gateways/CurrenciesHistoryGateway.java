package com.nazareth.currency.converter.gateways;

import com.nazareth.currency.converter.domains.mongo.Currencies;
import java.util.Set;

public interface CurrenciesHistoryGateway {

  Currencies save(Currencies currencies);

  Set<Currencies> findTop10ByUserUser(String username);
}
