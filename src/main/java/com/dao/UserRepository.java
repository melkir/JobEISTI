package com.dao;

import com.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {

    User findByUsername(String username);

    User findByEmail(String email);

}
