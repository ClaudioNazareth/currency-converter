package com.nazareth.currency.converter.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.nazareth.currency.converter.gateways.http.feign.jsons.CurrenciesResponse;
import java.util.HashMap;

public class CurrenciesResponseTemplateLoader implements TemplateLoader {

  public static final String VALID_CURRENCY_RESPONSE = "VALID_CURRENCY_RESPONSE";

  @Override
  public void load() {
    Fixture.of(CurrenciesResponse.class)
        .addTemplate(
            VALID_CURRENCY_RESPONSE,
            new Rule() {
              {
                add("timestamp", 154165477L);
                add("base", "USD");
                add("rates", new HashMap<>());
              }
            });
  }
}
