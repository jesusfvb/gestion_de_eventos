package com.backend.backend.service.implementation;

import com.backend.backend.repository.UserRepository;
import com.backend.backend.repository.entity.MyRole;
import com.backend.backend.repository.entity.MyUser;
import com.backend.backend.service.JwtService;
import com.backend.backend.service.MyUserDetailsService;
import com.backend.backend.service.RoleService;
import com.backend.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;
    private List<MyRole> roles;

    @Override
    public MyUser getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(String username, String password) {
        List<MyRole> roles = new LinkedList<>();
        roles.add(roleService.getRole(MyRole.Role.USER));
        userRepository.save(new MyUser(username, passwordEncoder.encode(password), roles));
    }

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
