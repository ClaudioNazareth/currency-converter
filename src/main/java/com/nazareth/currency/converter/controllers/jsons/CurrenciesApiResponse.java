package com.nazareth.currency.converter.controllers.jsons;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nazareth.currency.converter.domains.mongo.Currencies;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@ApiModel(value = "CurrenciesApiResponse", description = "Represents all currencies data")
public class CurrenciesApiResponse implements Serializable {

  private static final long serialVersionUID = 2045788603325170860L;

  @ApiModelProperty(
    value = "LocalDateTime yyyy-MM-dd'T'HH:mm:ss.SSS'Z' ",
    dataType = "DateTime",
    required = true,
    example = "2018-05-24 12:24:14"
  )
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  private LocalDateTime currencyDate;

  @ApiModelProperty(value = "Currency base", dataType = "String", required = true, example = "USD")
  private String currencyBase;

  @ApiModelProperty(value = "Representes all currencies rates", dataType = "Json", required = true)
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
