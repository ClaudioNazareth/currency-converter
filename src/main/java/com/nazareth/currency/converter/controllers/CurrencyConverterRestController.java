package com.nazareth.currency.converter.controllers;

import com.nazareth.currency.converter.controllers.jsons.CurrenciesApiResponse;
import com.nazareth.currency.converter.domains.mongo.Currencies;
import com.nazareth.currency.converter.usecases.GetCurrencies;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/currency-converter")
public class CurrencyConverterRestController {

  private GetCurrencies getCurrencies;

  @Autowired
  public CurrencyConverterRestController(GetCurrencies getCurrencies) {
    this.getCurrencies = getCurrencies;
  }

  @GetMapping(
    value = "/currencies",
    produces = {MediaType.APPLICATION_JSON_VALUE}
  )
  public CurrenciesApiResponse getCurrencies(
      @RequestParam(value = "date", required = false) String date) {
    return convertToJson(getCurrencies.getCurrencies(date));
  }

  @GetMapping(
    value = "/currencies-top-ten",
    produces = {MediaType.APPLICATION_JSON_VALUE}
  )
  public List<CurrenciesApiResponse> getTop10CurrenciesHistory() {

    final Set<Currencies> currenciesTop10ByUsername =
        getCurrencies.findTop10ByUsername(
            SecurityContextHolder.getContext().getAuthentication().getName());

    return convertToJson(currenciesTop10ByUsername);
  }

  private CurrenciesApiResponse convertToJson(Currencies Currencies) {
    return new CurrenciesApiResponse(Currencies);
  }

  private List<CurrenciesApiResponse> convertToJson(Set<Currencies> currencies) {
    return currencies.stream().map(CurrenciesApiResponse::new).collect(Collectors.toList());
  }
}
