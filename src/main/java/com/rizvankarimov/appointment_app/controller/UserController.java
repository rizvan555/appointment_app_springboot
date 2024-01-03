package com.rizvankarimov.appointment_app.controller;

import com.rizvankarimov.appointment_app.dto.UserDTO;
import com.rizvankarimov.appointment_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    public ResponseEntity<?> register(@RequestBody @Valid UserDTO user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
       userService.saveUser(user.convertToUser());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}