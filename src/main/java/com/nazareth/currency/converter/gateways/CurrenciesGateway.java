package com.nazareth.currency.converter.gateways;

import com.nazareth.currency.converter.gateways.http.feign.jsons.CurrenciesResponse;

public interface CurrenciesGateway {

  CurrenciesResponse getCurrencies();
}
