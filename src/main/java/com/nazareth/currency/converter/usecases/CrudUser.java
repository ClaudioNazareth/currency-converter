package com.nazareth.currency.converter.usecases;

import com.nazareth.currency.converter.domains.User;
import com.nazareth.currency.converter.gateways.UserGateway;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrudUser {

  private UserGateway userGateway;

  @Autowired
  public CrudUser(UserGateway userGateway) {
    this.userGateway = userGateway;
  }

  public Optional<User> findByUserName(String username) {
    return userGateway.findUserByUsername(username);
  }

  public User save(User user) {
    return userGateway.save(user);
  }
}
