package com.nazareth.currency.converter.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

  @Value("${spring.redis.expiration-time}")
  private Integer expirationTime;

  @Bean
  @Override
  public KeyGenerator keyGenerator() {
    return (target, method, params) -> {
      StringBuilder sb = new StringBuilder();
      sb.append(target.getClass().getName());
      sb.append(method.getName());
      for (Object obj : params) {
        sb.append(obj.toString());
      }
      return sb.toString();
    };
  }

  @Bean
  public StringRedisSerializer stringRedisSerializer() {
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    return stringRedisSerializer;
  }

  @Bean
  public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisJsonSerializer() {
    GenericJackson2JsonRedisSerializer genericJackson2JsonRedisJsonSerializer =
        new GenericJackson2JsonRedisSerializer();
    return genericJackson2JsonRedisJsonSerializer;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate(
      JedisConnectionFactory jedisConnectionFactory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory);
    redisTemplate.setExposeConnection(true);
    redisTemplate.setKeySerializer(stringRedisSerializer());
    redisTemplate.setValueSerializer(genericJackson2JsonRedisJsonSerializer());

    return redisTemplate;
  }

  @Bean
  public RedisCacheManager cacheManager(JedisConnectionFactory jedisConnectionFactory) {
    RedisCacheManager redisCacheManager =
        new RedisCacheManager(redisTemplate(jedisConnectionFactory));
    redisCacheManager.setTransactionAware(true);
    redisCacheManager.setLoadRemoteCachesOnStartup(true);
    redisCacheManager.setUsePrefix(true);
    redisCacheManager.setDefaultExpiration(30000);
    return redisCacheManager;
  }
}
