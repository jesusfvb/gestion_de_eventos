package com.backend.backend.service.implementation;

import com.backend.backend.service.JwtService;
import com.backend.backend.service.MyUserDetailsService;
import com.backend.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    // Authentication
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtService jwtService;

    @Override
    public String logIn(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        final String jwt = jwtService.generateToken(userDetails);
        return jwt;
    }
}
