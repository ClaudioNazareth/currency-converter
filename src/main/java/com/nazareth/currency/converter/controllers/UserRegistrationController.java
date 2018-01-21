package com.nazareth.currency.converter.controllers;

import com.nazareth.currency.converter.controllers.jsons.UserRequest;
import com.nazareth.currency.converter.domains.User;
import com.nazareth.currency.converter.usecases.CrudUser;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

  private CrudUser crudUser;

  @Autowired
  public UserRegistrationController(CrudUser crudUser) {
    this.crudUser = crudUser;
  }

  @ModelAttribute("user")
  public UserRequest userRegistrationDto() {
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
