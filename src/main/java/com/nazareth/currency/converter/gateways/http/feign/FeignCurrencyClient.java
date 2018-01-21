package com.nazareth.currency.converter.gateways.http.feign;

import com.nazareth.currency.converter.gateways.http.feign.jsons.CurrenciesResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${feign.currency.name}", url = "${feign.currency.url}")
public interface FeignCurrencyClient {

  @GetMapping(path = "/?app_id=8641c32db0a74dcfbe094fee093f8dbd")
  CurrenciesResponse getCurrencies();
}
