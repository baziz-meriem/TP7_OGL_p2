package com.telly.controllers;

import com.telly.dao.FormValidationGroup;
import com.telly.dao.User;
import com.telly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import java.security.Principal;

public class UserController {
    @Autowired
    UserService userService;
    public String showLogin() {
        return "login";
    }
    public String showLogout() {
        return "loggedout";
    }
    public String createAccount(Model model, Principal principal) {

        model.addAttribute("user", new User());

        return "createaccount";
    }
    public String createUser(@Validated(FormValidationGroup.class) User user, BindingResult result) {

        if(result.hasErrors()) {
            return "createaccount";
        }

        user.setAuthority("ROLE_USER");
        user.setEnabled(true);

        userService.create(user);

        return "home";

    }
}
