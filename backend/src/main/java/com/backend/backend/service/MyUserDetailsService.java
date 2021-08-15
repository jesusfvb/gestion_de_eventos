package com.backend.backend.service;

import com.backend.backend.repository.entity.MyRole;
import com.backend.backend.repository.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MyUser user = userService.getByUsername(s);
        List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        roleService.getByUsername(s).forEach(myRole -> {
            authorities.add(new SimpleGrantedAuthority(myRole.getRol().name()));
        });
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
