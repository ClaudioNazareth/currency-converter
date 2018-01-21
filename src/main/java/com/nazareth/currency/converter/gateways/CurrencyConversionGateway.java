package com.nazareth.currency.converter.gateways;

import com.nazareth.currency.converter.domains.CurrencyConversion;
import com.nazareth.currency.converter.domains.User;
import java.util.List;
import java.util.Optional;

public interface CurrencyConversionGateway {

  CurrencyConversion save(CurrencyConversion currencyConversion);

  Optional<List<CurrencyConversion>> findTop10ByUser(User user);
}
