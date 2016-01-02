package com.controller;

import com.dao.UserRepository;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/register")
public class Registration {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user, Model model) {
        userRepository.save(user);
        model.addAttribute("user", user);
        return "userDetails";
    }

}
