package com.rizvankarimov.appointment_app.service;

import com.rizvankarimov.appointment_app.model.Role;
import com.rizvankarimov.appointment_app.model.User;

import java.util.List;

public interface UserService {

    com.rizvankarimov.appointment_app.model.User saveUser(User user);

    User changeRole(Role newRole, String username);

    User findByUsername(String username);

    User deleteUser(Long id);

    List<User> findAllUsers();
}
