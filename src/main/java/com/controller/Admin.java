package com.controller;

import com.dao.UserRepository;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class Admin {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        final List<User> users = userRepository.findAll();
        Long countUser = userRepository.count();
        model.addAttribute("users", users);
        model.addAttribute("countUser", countUser);
        return "admin/index";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showStats(Model model) {
        Long countUser = userRepository.count();
        model.addAttribute("countUser", countUser);
        // Todo ajouter des informations sur les newsletters, ressources etc...
        return "admin";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String updateUserById(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findOne(id);
        model.addAttribute(user);
        return "admin/editUser";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String updateUserById(@PathVariable("id") Long id, @ModelAttribute User user) {
        try {
            userRepository.updateUser(id, user);
            return String.format("User [%s] successfully edited", id);
        } catch (Exception e) {
            return String.format("A problem occurred when editing User [%s]", e.getMessage());
        }
    }

    @RequestMapping(value = "/user/{id}/delete", method = RequestMethod.GET)
    @ResponseBody
    public String deleteUserById(@PathVariable("id") Long id) {
        try {
            userRepository.delete(id);
            return String.format("User [%s] successfully deleted", id); // uses the delete() method inherited from CrudRepository
        } catch (Exception e) {
            return String.format("A problem occurred when deleting user [%s]", e.getMessage());
        }
    }

}
