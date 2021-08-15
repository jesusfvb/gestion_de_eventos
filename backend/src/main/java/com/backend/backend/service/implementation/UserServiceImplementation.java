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

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public String logIn(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        final String jwt = jwtService.generateToken(userDetails);
        return jwt;
    }

    @Override
    public List<MyUser> list() {
        return userRepository.findAll();
    }

    @Override
    public MyUser getById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public MyUser getByUsername(String username) {
        if (userRepository.findAll().isEmpty()) {
            MyUser userAux = userRepository.save(new MyUser(username, username, username, passwordEncoder.encode(username), 666));
            roleService.addRoleUser(MyRole.Role.USER, userAux);
            roleService.addRoleUser(MyRole.Role.ADMINISTRATION, userAux);
            return userAux;
        }
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(String name, String surname, String username, Integer dni) {
        MyUser userAux = userRepository.save(new MyUser(name, surname, username, passwordEncoder.encode(username), dni));
        roleService.addRoleUser(MyRole.Role.USER, userAux);
    }

    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            roleService.removeAllRoleUserId(id);
            userRepository.deleteById(id);
        }
    }

    @Override
    public void update(Integer id, String name, String surname, String username, Integer dni) {
        MyUser user = userRepository.findById(id).get();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setDni(dni);
        userRepository.save(user);
    }
}
