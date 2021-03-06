package com.dao;

import com.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAll();

}
