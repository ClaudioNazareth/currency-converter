package com.nazareth.currency.converter.gateways.h2;

import com.nazareth.currency.converter.domains.jpa.User;
import com.nazareth.currency.converter.gateways.UserGateway;
import com.nazareth.currency.converter.gateways.h2.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserGatewayImpl implements UserGateway {

  private UserRepository userRepository;

  @Autowired
  public UserGatewayImpl(UserRepository userRepository) {
    Assert.notNull(userRepository, "UserRepository is required");
    this.userRepository = userRepository;
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public Optional<User> findUserByUsername(String username) {
    return userRepository.findByEmail(username);
  }
}
