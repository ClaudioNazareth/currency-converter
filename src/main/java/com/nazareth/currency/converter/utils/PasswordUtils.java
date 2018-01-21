package com.nazareth.currency.converter.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Uses BCryptPasswordEncoder to encrypt a password
 *
 * @author Claudio Nazareth
 */
public class PasswordUtils {

  /**
   * @param password | String password
   * @return encrypted password, null if the password is null or empty string if the password is
   *     empty
   */
  public static String generateBCrypt(String password) {

    if (password == null || password.isEmpty()) return password;

    return new BCryptPasswordEncoder().encode(password);
  }
}
