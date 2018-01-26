package com.nazareth.currency.converter.gateways.mongo;

import com.nazareth.currency.converter.domains.mongo.Currencies;
import com.nazareth.currency.converter.gateways.CurrenciesHistoryGateway;
import com.nazareth.currency.converter.gateways.mongo.repositories.CurrenciesRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrenciesHistoryGatewayImpl implements CurrenciesHistoryGateway {

  private CurrenciesRepository currenciesRepository;

  @Autowired
  public CurrenciesHistoryGatewayImpl(CurrenciesRepository currenciesRepository) {
    this.currenciesRepository = currenciesRepository;
  }

  @Override
  public Currencies save(Currencies currencies) {
    currencies.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    return currenciesRepository.save(currencies);
  }

  @Override
  @Cacheable(value = "findTop10ByUserUser", key = "#username")
  public Set<Currencies> findTop10ByUserUser(String username) {
    return currenciesRepository.findTop10ByUsernameOrderByIdDesc(username);
  }
}
