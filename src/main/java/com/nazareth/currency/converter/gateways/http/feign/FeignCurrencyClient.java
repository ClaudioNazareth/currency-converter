package com.nazareth.currency.converter.gateways.http.feign;

import com.nazareth.currency.converter.gateways.http.feign.jsons.CurrenciesResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${feign.currency.name}", url = "${feign.currency.url}")
public interface FeignCurrencyClient {

  @GetMapping(path = "/latest.json/?app_id=8641c32db0a74dcfbe094fee093f8dbd")
  CurrenciesResponse getCurrencies();

  @GetMapping(path = "/historical/{date}?app_id=8641c32db0a74dcfbe094fee093f8dbd")
  CurrenciesResponse getHistoricalCurrencies(@PathVariable("date") String date);
}
