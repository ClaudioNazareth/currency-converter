package com.nazareth.currency.converter.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.nazareth.currency.converter.controllers.jsons.UserRequest;

public class UserRequestTemplateLoader implements TemplateLoader {

  public static final String USER_REQUEST = "USER_REQUEST";

  @Override
  public void load() {

    Fixture.of(UserRequest.class)
        .addTemplate(
            USER_REQUEST,
            new Rule() {
              {
                add("firstName", "Claudio");
                add("lastName", "Nazareth");
                add("password", "2wsxzaq1");
                add("confirmPassword", "2wsxzaq1");
                add("email", "chtnazareth@gmail.com");
                add("confirmEmail", "chtnazareth@gmail.com");
                add("terms", true);
              }
            });
  }
}
