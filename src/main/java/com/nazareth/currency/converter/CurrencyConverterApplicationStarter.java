package com.nazareth.currency.converter;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import redis.embedded.RedisServer;

@SpringBootApplication
@EnableFeignClients
public class CurrencyConverterApplicationStarter {

  public static void main(String[] args) {
    SpringApplication.run(CurrencyConverterApplicationStarter.class, args);
  }

  @Bean
  public RedisServer redis() {
    RedisServer redisServer = null;

    try {
      //      logger.info("Starting redis on port 6379");
      redisServer = new RedisServer(6379);
    } catch (IOException e) {
      //      logger.error("Error to starting Redis", e);
      e.printStackTrace();
    }
    redisServer.start();
    //    logger.info("Redis started");
    return redisServer;
  }
}
