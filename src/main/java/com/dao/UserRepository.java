package com.dao;

import com.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    User findByEmail(String email);
}
