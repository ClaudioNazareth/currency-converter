package com.nazareth.currency.converter.controllers;

import com.nazareth.currency.converter.domains.mongo.Currencies;
import com.nazareth.currency.converter.usecases.GetCurrencies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Default Controller to handle Mvc pages request
 *
 * @author Claudio Nazareth
 */
@Controller
public class DefaultController {

  private GetCurrencies getCurrencies;

  @Autowired
  public DefaultController(GetCurrencies getCurrencies) {
    Assert.notNull(getCurrencies, "GetCurrencies is required");
    this.getCurrencies = getCurrencies;
  }

  @GetMapping("/")
  public String root(Model model) {

    model.addAttribute(
        "currenciesHistory",
        getCurrencies.findTop10ByUsername(
            SecurityContextHolder.getContext().getAuthentication().getName()));
    model.addAttribute("currency", new Currencies());

    return "index";
  }

  @GetMapping("/login")
  public String login(Model model) {
    return "login";
  }

  @GetMapping("/user")
  public String userIndex() {
    return "user/index";
  }

  @GetMapping("/403")
  public String error403() {
    return "/error/403";
  }
}
