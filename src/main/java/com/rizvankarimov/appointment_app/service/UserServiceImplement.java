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
public class UserServiceImplement implements UserService{
    @Autowired
    private UserRepository userRepository; // this is an interface

    @Autowired
    public PasswordEncoder passwordEncoder; // this is an interface



    @Override   // this is for saving a user
    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateDate(LocalDateTime.now());
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }

    @Override    // this is for changing a user's role
    public User changeRole(Role newRole, String username){
        User user = UserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException());
        return userRepository.save(user);
    }

    @Override  // this is for finding a user by username
    public User findByUsername(String username){
        return UserRepository.findByUsername(username).orElseThrow(() -> new RuntimeException());
    }

    @Override  // this is for deleting a user
    public User deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
        userRepository.delete(user);
        return user;
    }

    @Override  // this is for finding all users
    public List<User> findAllUsers(){
        return userRepository.findAll();

    }
}
