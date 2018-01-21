package com.nazareth.currency.converter.domains;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "CURRENCY_CONVERSION_HISTORY")
public class CurrencyConversion extends BaseEntity {

  @NotEmpty
  @Column(name = "AMOUNT")
  private Double amount;

  @NotEmpty
  @Column(name = "FROM")
  private String from;

  @NotEmpty
  @Column(name = "TO")
  private String to;

  @NotEmpty
  @Column(name = "RATE")
  private Double rate;

  @NotEmpty
  @Column(name = "RESULT")
  private Double result;

  @Column(name = "CONVERSION_DATE")
  private LocalDateTime conversionDate;

  @ManyToOne private User user;

  CurrencyConversion() {}

  public CurrencyConversion(
      Double amount, String from, String to, Double rate, Double result, User user) {
    this.amount = amount;
    this.from = from;
    this.to = to;
    this.rate = rate;
    this.result = result;
    this.user = user;
  }

  public String getFrom() {
    return from;
  }

  public String getTo() {
    return to;
  }

  public Double getAmount() {
    return amount;
  }

  public Double getRate() {
    return rate;
  }

  public Double getResult() {
    return result;
  }

  public User getUser() {
    return user;
  }
}
