package com.rizvankarimov.appointment_app.service;

import com.rizvankarimov.appointment_app.model.Role;
import com.rizvankarimov.appointment_app.model.User;
import com.rizvankarimov.appointment_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepository; // this is an interface

    @Autowired
    private PasswordEncoder passwordEncoder; // this is an interface

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateDate(LocalDateTime.now());
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }

    @Override
    public User changeRole(Role newRole, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException());
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
        userRepository.delete(user);
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
