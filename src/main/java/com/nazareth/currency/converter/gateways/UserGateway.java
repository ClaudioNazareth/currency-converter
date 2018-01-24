package com.nazareth.currency.converter.gateways;

import com.nazareth.currency.converter.domains.jpa.User;
import java.util.Optional;

public interface UserGateway {

  User save(User user);

  Optional<User> findUserByUsername(String username);
}
