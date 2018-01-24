package com.nazareth.currency.converter.gateways.http;

import com.nazareth.currency.converter.domains.mongo.Currencies;
import com.nazareth.currency.converter.gateways.CurrenciesGateway;
import com.nazareth.currency.converter.gateways.http.feign.FeignCurrencyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CurrenciesGatewayImpl implements CurrenciesGateway {

  private FeignCurrencyClient feignCurrencyClient;

  @Autowired
  public CurrenciesGatewayImpl(FeignCurrencyClient feignCurrencyClient) {
    this.feignCurrencyClient = feignCurrencyClient;
  }

  @Override
  @Cacheable
  public Currencies getCurrencies() {
    return feignCurrencyClient.getCurrencies().toDomain();
  }

  @Override
  @Cacheable
  public Currencies getHistoricalCurrencies(String date) {
    StringBuilder stringBuilder = new StringBuilder(date);
    stringBuilder.append(".json");
    return feignCurrencyClient.getHistoricalCurrencies(stringBuilder.toString()).toDomain();
  }
}
