package com.nazareth.currency.converter.usecases;

import com.nazareth.currency.converter.domains.jpa.Role;
import com.nazareth.currency.converter.domains.jpa.User;
import com.nazareth.currency.converter.gateways.UserGateway;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Usa case responsible to authenticate the user on Spring Security
 *
 * @author Claudio Nazareth
 */
@Service
public class AuthenticateUser implements UserDetailsService {

  private UserGateway userGateway;

  @Autowired
  public AuthenticateUser(UserGateway userGateway) {
    Assert.notNull(userGateway, "UserGateway is required");
    this.userGateway = userGateway;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userOptional = userGateway.findUserByUsername(username);

    if (userOptional.isPresent()) {
      final User user = userOptional.get();
      return new org.springframework.security.core.userdetails.User(
          user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));

    } else {
      throw new UsernameNotFoundException("Invalid username or password.");
    }
  }

  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
    return roles
        .stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());
  }
}
