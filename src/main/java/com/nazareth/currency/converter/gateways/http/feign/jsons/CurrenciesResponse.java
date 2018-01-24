package com.nazareth.currency.converter.gateways.http.feign.jsons;

import com.nazareth.currency.converter.domains.mongo.Currencies;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TimeZone;

public class CurrenciesResponse implements Serializable {

  private static final long serialVersionUID = 6551830525712037443L;

  private long timestamp;

  private String base;

  private Map<String, Double> rates;

  CurrenciesResponse() {}

  public Currencies toDomain() {

    LocalDateTime currencyDate =
        LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), TimeZone.getDefault().toZoneId());

    return new Currencies(currencyDate, base, rates);
  }

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
