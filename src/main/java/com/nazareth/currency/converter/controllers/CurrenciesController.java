package com.nazareth.currency.converter.controllers;

import com.nazareth.currency.converter.usecases.GetCurrencies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Spring MVC Controller tho handle the Currencies Page
 *
 * @author Claudio Nazareth
 */
@Controller
@RequestMapping("/currencies")
public class CurrenciesController {

  private GetCurrencies getCurrencies;

  @Autowired
  public CurrenciesController(GetCurrencies getCurrencies) {
    Assert.notNull(getCurrencies, "GetCurrencies is required");
    this.getCurrencies = getCurrencies;
  }

  @Cacheable(value = "getCurrencies", key = "#date")
  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public String getCurrencies(
      @RequestParam(value = "date", required = false) String date, Model model) {

    model.addAttribute("currency", getCurrencies.getCurrencies(date));

    model.addAttribute(
        "currenciesHistory",
        getCurrencies.findTop10ByUsername(
            SecurityContextHolder.getContext().getAuthentication().getName()));
    return "index";
  }
}
