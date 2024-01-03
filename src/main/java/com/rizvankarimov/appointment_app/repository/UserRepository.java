package com.rizvankarimov.appointment_app.repository;

import com.rizvankarimov.appointment_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByUsername(String username);  // this is for finding a user by username

}
