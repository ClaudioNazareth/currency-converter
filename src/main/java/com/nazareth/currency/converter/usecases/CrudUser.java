package com.nazareth.currency.converter.usecases;

import com.nazareth.currency.converter.domains.jpa.User;
import com.nazareth.currency.converter.gateways.UserGateway;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Use case to register and find Users
 *
 * @author Claudio Nazareth
 */
@Service
public class CrudUser {

  private UserGateway userGateway;

  @Autowired
  public CrudUser(UserGateway userGateway) {
    Assert.notNull(userGateway, "UserGateway is required");
    this.userGateway = userGateway;
  }

  public Optional<User> findByUserName(String username) {
    return userGateway.findUserByUsername(username);
  }

  public User save(User user) {
    return userGateway.save(user);
  }
}
