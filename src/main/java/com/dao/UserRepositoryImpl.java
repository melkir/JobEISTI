package com.dao;

import com.model.User;
import com.model.UserRoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

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
}
