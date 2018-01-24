package com.nazareth.currency.converter.gateways.mongo.repositories;

import com.nazareth.currency.converter.domains.mongo.Currencies;
import java.util.Set;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrenciesRepository extends MongoRepository<Currencies, String> {

  Set<Currencies> findTop10ByUsernameOrderByIdDesc(String username);
}
