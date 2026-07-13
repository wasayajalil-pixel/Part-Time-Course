package com.axsos.gameM.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.axsos.gameM.models.User;


public interface UserRepository extends CrudRepository<User, Long> {
    // Find user by email for login
    Optional<User> findByEmail(String email);

    // Check if email already exists during registration
    boolean existsByEmail(String email);

}
