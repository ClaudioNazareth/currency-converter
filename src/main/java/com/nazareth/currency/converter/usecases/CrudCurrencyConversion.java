package com.nazareth.currency.converter.usecases;

import com.nazareth.currency.converter.domains.CurrencyConversion;
import com.nazareth.currency.converter.domains.User;
import com.nazareth.currency.converter.gateways.CurrencyConversionGateway;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrudCurrencyConversion {

  private CurrencyConversionGateway currencyConversionGateway;

  private CrudUser crudUser;

  @Autowired
  public CrudCurrencyConversion(
      CurrencyConversionGateway currencyConversionGateway, CrudUser crudUser) {
    this.currencyConversionGateway = currencyConversionGateway;
    this.crudUser = crudUser;
  }

  public CurrencyConversion save(CurrencyConversion currencyConversion) {
    return currencyConversionGateway.save(currencyConversion);
  }

  public Optional<List<CurrencyConversion>> findTop10ByUsername(String username) {
    final Optional<User> userOptional = crudUser.findByUserName(username);
    if (userOptional.isPresent()) {
      return currencyConversionGateway.findTop10ByUser(userOptional.get());
    } else {
      // TODO aqui
      throw new RuntimeException();
    }
  }
}
