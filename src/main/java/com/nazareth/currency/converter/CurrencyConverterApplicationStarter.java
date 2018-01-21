package com.nazareth.currency.converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyConverterApplicationStarter {

  public static void main(String[] args) {
    SpringApplication.run(CurrencyConverterApplicationStarter.class, args);
  }
}
