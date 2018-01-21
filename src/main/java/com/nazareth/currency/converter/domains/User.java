package com.nazareth.currency.converter.domains;

import static com.nazareth.currency.converter.utils.PasswordUtils.generateBCrypt;

import java.util.Arrays;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USER", uniqueConstraints = @UniqueConstraint(columnNames = "EMAIL"))
public class User extends BaseEntity {

  @NotEmpty
  @Column(name = "FIRST_NAME")
  private String firstName;

  @NotEmpty
  @Column(name = "LAST_NAME")
  private String lastName;

  @NotEmpty
  @Column(name = "EMAIL")
  private String email;

  @NotEmpty
  @Column(name = "PASSWORD")
  private String password;

  public User(String firstName, String lastName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = generateBCrypt(password);
    this.roles = Arrays.asList(new Role("ROLE_USER"));
  }

  User() {}

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
    name = "USERS_ROLES",
    joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
  )
  private Collection<Role> roles;

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public Collection<Role> getRoles() {
    return roles;
  }
}
