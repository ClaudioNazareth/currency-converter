package com.nazareth.currency.converter.controllers.jsons;

import com.nazareth.currency.converter.domains.jpa.User;
import com.nazareth.currency.converter.security.FieldMatch;
import javax.validation.constraints.AssertTrue;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@FieldMatch.List({
  @FieldMatch(
    first = "password",
    second = "confirmPassword",
    message = "The password fields must match"
  ),
  @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class UserRequest {

  @NotEmpty private String firstName;

  @NotEmpty private String lastName;

  @NotEmpty private String password;

  @NotEmpty private String confirmPassword;

  @Email @NotEmpty private String email;

  @Email @NotEmpty private String confirmEmail;

  @AssertTrue private Boolean terms;

  public User toDomain() {
    return new User(firstName, lastName, email, password);
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPassword() {
    return password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public String getEmail() {
    return email;
  }

  public String getConfirmEmail() {
    return confirmEmail;
  }

  public Boolean getTerms() {
    return terms;
  }
}
