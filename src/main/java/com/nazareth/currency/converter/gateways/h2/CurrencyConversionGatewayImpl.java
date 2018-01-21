package com.nazareth.currency.converter.gateways.h2;

import com.nazareth.currency.converter.domains.CurrencyConversion;
import com.nazareth.currency.converter.domains.User;
import com.nazareth.currency.converter.gateways.CurrencyConversionGateway;
import com.nazareth.currency.converter.gateways.h2.repositories.CurrencyConversionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionGatewayImpl implements CurrencyConversionGateway {

  private CurrencyConversionRepository currencyConversionRepository;

  @Autowired
  public CurrencyConversionGatewayImpl(CurrencyConversionRepository currencyConversionRepository) {
    this.currencyConversionRepository = currencyConversionRepository;
  }

  @Override
  public CurrencyConversion save(CurrencyConversion currencyConversion) {
    return currencyConversionRepository.save(currencyConversion);
  }

  @Override
  public Optional<List<CurrencyConversion>> findTop10ByUser(User user) {
    return currencyConversionRepository.findTop10ByUser(user);
  }
}
