package com.nazareth.currency.converter.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.nazareth.currency.converter.domains.mongo.Currencies;
import java.time.LocalDateTime;
import java.util.HashMap;

public class CurrenciesTemplateLoader implements TemplateLoader {

  public static final String VALID_CURRENCY = "VALID_CURRENCY";

  @Override
  public void load() {

    Fixture.of(Currencies.class)
        .addTemplate(
            VALID_CURRENCY,
            new Rule() {
              {
                add("currencyDate", LocalDateTime.now());
                add("currencyBase", "USD");
                add("rates", new HashMap<>());
              }
            });
  }
}
