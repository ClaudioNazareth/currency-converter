package com.nazareth.currency.converter.domains;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
abstract class BaseEntity {

  @Id
  @GeneratedValue(generator = "ID_GENERATOR")
  @Column(name = "ID", updatable = false, nullable = false)
  private Long id;

  @Column(name = "CREATED_ON", nullable = false, insertable = false, updatable = false)
  @CreationTimestamp
  private LocalDateTime createdOn;

  @Column(name = "UPDATED_ON", nullable = false, insertable = false, updatable = false)
  @UpdateTimestamp
  private LocalDateTime updatedOn;

  @Version
  @Column(name = "OPT_LOCK", insertable = false, updatable = false)
  public Integer optimisticLock;
}
