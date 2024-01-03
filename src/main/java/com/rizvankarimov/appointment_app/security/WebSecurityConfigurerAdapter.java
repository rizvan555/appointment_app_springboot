package com.rizvankarimov.appointment_app.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public abstract class WebSecurityConfigurerAdapter {
    // this is for configuring the http security
    protected abstract void configure(HttpSecurity http) throws Exception;

    // this is for configuring the authentication manager builder
    protected abstract void configure(AuthenticationManagerBuilder auth) throws Exception;
}
