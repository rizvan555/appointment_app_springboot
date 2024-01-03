package com.rizvankarimov.appointment_app.security;

import com.rizvankarimov.appointment_app.model.User;
import com.rizvankarimov.appointment_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImplement implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = UserRepository
                .findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));                    // this is for finding a user by username
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();                                                      // this is for setting the user's role
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().name()));                                       // this is for setting the user's role
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);  // this is for returning the user's username, password, and role
    }
}
