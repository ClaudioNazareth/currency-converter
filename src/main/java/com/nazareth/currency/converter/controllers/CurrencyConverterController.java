package com.nazareth.currency.converter.controllers;

import com.nazareth.currency.converter.domains.CurrencyConversion;
import com.nazareth.currency.converter.usecases.ConvertCurrency;
import com.nazareth.currency.converter.usecases.CrudCurrencyConversion;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/currency-converter")
public class CurrencyConverterController {

  private ConvertCurrency convertCurrency;

  private CrudCurrencyConversion crudCurrencyConversion;

  @Autowired
  public CurrencyConverterController(
      ConvertCurrency convertCurrency, CrudCurrencyConversion crudCurrencyConversion) {
    this.convertCurrency = convertCurrency;
    this.crudCurrencyConversion = crudCurrencyConversion;
  }

  @GetMapping(
    value = "/last-ten-queries",
    produces = {MediaType.APPLICATION_JSON_VALUE}
  )
  public List<CurrencyConversion> getLastTenQueries() {

    final Optional<List<CurrencyConversion>> top10ByUsernameOptional =
        crudCurrencyConversion.findTop10ByUsername(
            SecurityContextHolder.getContext().getAuthentication().getName());

    if (top10ByUsernameOptional.isPresent()) {
      return top10ByUsernameOptional.get();
    }

    return new ArrayList<>();
  }

  @GetMapping(
    value = "/convert",
    produces = {MediaType.APPLICATION_JSON_VALUE}
  )
  public void convert(
      @RequestParam(value = "date", required = false) String date,
      @RequestParam(value = "amount", required = true) Double amount,
      @RequestParam(value = "from", required = true) String from,
      @RequestParam(value = "to", required = true) Double to) {}
}
