package com.dao;

import com.model.User;

public interface UserRepositoryCustom {
    void createUser(User user);

    void updateUser(Long id, User user);

    String resetPassword(User user);

}
