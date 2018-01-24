package com.nazareth.currency.converter.domains.mongo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "currenciesHistory")
public class Currencies {

  @Id private String id;

  @NotNull
  @Indexed
  @Field(value = "currencyDate")
  private LocalDateTime currencyDate;

  @NotNull
  @Field(value = "currencyBase")
  private String currencyBase;

  @NotNull
  @Field(value = "rates")
  private Map<String, Double> rates;

  @NotNull
  @Indexed
  @Field(value = "username")
  private String username;

  public Currencies(LocalDateTime currencyDate, String currencyBase, Map<String, Double> rates) {
    this.currencyDate = currencyDate;
    this.currencyBase = currencyBase;
    this.rates = rates;
  }

  public Currencies() {}

  public String getCurrencyDateFormatted() {

    if (currencyDate != null) {

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

      return currencyDate.format(formatter);
    }

    return "";
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

  public void setUsername(String username) {
    this.username = username;
  }
}
