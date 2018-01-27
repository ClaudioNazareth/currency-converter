package com.nazareth.currency.converter.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.nazareth.currency.converter.domains.jpa.User;

public class UserTemplateLoader implements TemplateLoader {

  public static final String USER = "USER";

  @Override
  public void load() {

    Fixture.of(User.class)
        .addTemplate(
            USER,
            new Rule() {
              {
                add("firstName", "Claudio");
                add("lastName", "Nazareth");
                add("password", "!@fsdmJIDAS#$%FGHFH");
                add("email", "chtnazareth@gmail.com");
              }
            });
  }
}
