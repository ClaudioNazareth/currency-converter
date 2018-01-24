package com.nazareth.currency.converter.domains.jpa;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", updatable = false, nullable = false)
  private Long id;

  @Version
  @Column(name = "OPT_LOCK", insertable = false, updatable = false)
  public Integer optimisticLock;
}
