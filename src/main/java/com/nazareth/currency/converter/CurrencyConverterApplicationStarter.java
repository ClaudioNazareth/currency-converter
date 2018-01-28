package com.nazareth.currency.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyConverterApplicationStarter {

  private static final Logger logger =
      LoggerFactory.getLogger(CurrencyConverterApplicationStarter.class);

  public static void main(String[] args) {
    SpringApplication.run(CurrencyConverterApplicationStarter.class, args);
  }
}
