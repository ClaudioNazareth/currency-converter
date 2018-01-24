package com.nazareth.currency.converter.controllers.jsons;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nazareth.currency.converter.domains.mongo.Currencies;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

public class CurrenciesApiResponse implements Serializable {

  private static final long serialVersionUID = 2045788603325170860L;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  private LocalDateTime currencyDate;

  private String currencyBase;

  private Map<String, Double> rates;

  public CurrenciesApiResponse(Currencies currencies) {
    this.currencyDate = currencies.getCurrencyDate();
    this.currencyBase = currencies.getCurrencyBase();
    this.rates = currencies.getRates();
  }

  public LocalDateTime getCurrencyDate() {
    return currencyDate;
  }

  public String getCurrencyBase() {
    return currencyBase;
  }

  public Map<String, Double> getRates() {
    return rates;
  }
}
