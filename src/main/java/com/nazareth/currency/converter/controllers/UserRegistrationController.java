package com.nazareth.currency.converter.controllers;

import com.nazareth.currency.converter.controllers.jsons.UserRequest;
import com.nazareth.currency.converter.domains.jpa.User;
import com.nazareth.currency.converter.usecases.CrudUser;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller responsible to register a new user
 *
 * @author Claudio Nazareht
 */
@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

  private CrudUser crudUser;

  @Autowired
  public UserRegistrationController(CrudUser crudUser) {
    Assert.notNull(crudUser, "CrudUser is required");
    this.crudUser = crudUser;
  }

  @ModelAttribute("user")
  public UserRequest userRequest() {
    return new UserRequest();
  }

  @GetMapping
  public String showRegistrationForm(Model model) {
    return "registration";
  }

  @PostMapping
  public String registerUserAccount(
      @ModelAttribute("user") @Valid UserRequest userRequest, BindingResult result) {

    Optional<User> optionalUser = crudUser.findByUserName(userRequest.getEmail());

    if (!optionalUser.isPresent()) {

      if (result.hasErrors()) {
        return "registration";
      }

      crudUser.save(userRequest.toDomain());

    } else {
      result.rejectValue("email", null, "There is already an account registered with that  email");
    }

    return "redirect:/registration?success";
  }
}
