package com.controller;

import com.dao.UserRepository;
import com.model.User;
import com.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/login/recover")
public class Recover {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;

    @RequestMapping(method = RequestMethod.GET)
    public String resetPasswordForm() {
        return "recover_password";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String resetPassword(@ModelAttribute("email") String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) return "No user found for this email";
        String newPassword = userRepository.resetPassword(user);
        String from = "no-reply@jobeisti.com";
        String to = user.getEmail();
        String subject = "Password reset instruction for JobEISTI";
        String body = "Bonjour " + user.getFirstName() + " " + user.getLastName()
                + "\n" + "Votre nouveau mot de passe est  : " + newPassword;
        mailService.sendMail(from, to, subject, body);
        return "An email with your new password has been send to " + email;
    }
}
