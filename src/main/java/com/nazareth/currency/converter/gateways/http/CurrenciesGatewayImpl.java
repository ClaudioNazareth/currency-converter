package com.nazareth.currency.converter.gateways.http;

import com.nazareth.currency.converter.gateways.CurrenciesGateway;
import com.nazareth.currency.converter.gateways.http.feign.FeignCurrencyClient;
import com.nazareth.currency.converter.gateways.http.feign.jsons.CurrenciesResponse;
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
  public CurrenciesResponse getCurrencies() {
    return feignCurrencyClient.getCurrencies();
  }
}
