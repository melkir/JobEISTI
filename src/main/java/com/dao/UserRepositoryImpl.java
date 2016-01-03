package com.dao;

import com.model.User;
import com.model.UserRoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

public class UserRepositoryImpl implements UserRepositoryCustom {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createUser(User user) {
        String userRole = UserRoleType.USER.getRole();
        user.addRole(userRole); // attach the user role by default
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public String resetPassword(User user) {
        String password = generateRandomPassword(8);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return password;
    }

    private String generateRandomPassword(int len) {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
