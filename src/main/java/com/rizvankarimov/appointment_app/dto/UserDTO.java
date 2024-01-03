package com.rizvankarimov.appointment_app.dto;

import com.rizvankarimov.appointment_app.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


public class UserDTO {
    @Getter
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;


    public User convertToUser(){
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setPhone(this.phone);
        return user;
    }
}
