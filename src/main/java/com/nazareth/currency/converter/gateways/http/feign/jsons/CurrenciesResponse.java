package com.nazareth.currency.converter.gateways.http.feign.jsons;

import java.io.Serializable;
import java.util.Map;

public class CurrenciesResponse implements Serializable {

  private static final long serialVersionUID = 6551830525712037443L;

  private long timestamp;

  private String base;

  private Map<String, Double> rates;

  CurrenciesResponse() {}

  public long getTimestamp() {
    return timestamp;
  }

  public String getBase() {
    return base;
  }

  public Map<String, Double> getRates() {
    return rates;
  }
}
