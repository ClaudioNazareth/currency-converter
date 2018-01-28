package com.nazareth.currency.converter.controllers;

import com.nazareth.currency.converter.controllers.jsons.CurrenciesApiResponse;
import com.nazareth.currency.converter.domains.mongo.Currencies;
import com.nazareth.currency.converter.usecases.GetCurrencies;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/currency-converter")
@Api(
  value = "CurrencyConverter",
  consumes = MediaType.APPLICATION_JSON_VALUE,
  produces = MediaType.APPLICATION_JSON_VALUE,
  tags = {"Endpoint to get currencies by date"},
  description = "Rest API for get currencies by date",
  basePath = "/api/v1/currency-converter"
)
public class CurrencyConverterRestController {

  private GetCurrencies getCurrencies;

  @Autowired
  public CurrencyConverterRestController(GetCurrencies getCurrencies) {
    Assert.notNull(getCurrencies, "GetCurrencies is required");
    this.getCurrencies = getCurrencies;
  }

  @GetMapping(
    value = "/currencies",
    produces = {MediaType.APPLICATION_JSON_VALUE}
  )
  @ApiOperation(
    value = "get currencies by date",
    notes = "Return the the base currency USA and all other Currencies values based on USD",
    response = CurrenciesApiResponse.class
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 500, message = "Internal Server Error")
    }
  )
  public CurrenciesApiResponse getCurrencies(
      @RequestParam(value = "date", required = false) String date) {
    return convertToJson(
        getCurrencies.getCurrencies(
            date, SecurityContextHolder.getContext().getAuthentication().getName()));
  }

  @GetMapping(
    value = "/currencies-top-ten",
    produces = {MediaType.APPLICATION_JSON_VALUE}
  )
  @ApiOperation(
    value = "Get the last ten queries made by user",
    response = CurrenciesApiResponse.class
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "ok"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 500, message = "Internal Server Error")
    }
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
