package com.nazareth.currency.converter.gateways.h2.repositories;

import com.nazareth.currency.converter.domains.CurrencyConversion;
import com.nazareth.currency.converter.domains.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversion, Long> {

  Optional<List<CurrencyConversion>> findTop10ByUser(User user);
}
