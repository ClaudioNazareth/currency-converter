package com.nazareth.currency.converter.gateways.mongo;

import com.nazareth.currency.converter.domains.mongo.Currencies;
import com.nazareth.currency.converter.gateways.CurrenciesHistoryGateway;
import com.nazareth.currency.converter.gateways.mongo.repositories.CurrenciesRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CurrenciesHistoryGatewayImpl implements CurrenciesHistoryGateway {

  private CurrenciesRepository currenciesRepository;

  @Autowired
  public CurrenciesHistoryGatewayImpl(CurrenciesRepository currenciesRepository) {
    Assert.notNull(currenciesRepository, "UserRepository is required");
    this.currenciesRepository = currenciesRepository;
  }

  @Override
  public Currencies save(Currencies currencies, String username) {
    currencies.setUsername(username);
    return currenciesRepository.save(currencies);
  }

  @Override
  @Cacheable(value = "findTop10ByUserUser", key = "#username")
  public Set<Currencies> findTop10ByUserUser(String username) {
    return currenciesRepository.findTop10ByUsernameOrderByIdDesc(username);
  }
}
