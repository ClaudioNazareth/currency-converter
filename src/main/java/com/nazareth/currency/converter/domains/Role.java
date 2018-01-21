package com.nazareth.currency.converter.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "ROLE", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class Role extends BaseEntity {

  @NotEmpty
  @Column(name = "NAME")
  private String name;

  Role() {}

  public Role(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
