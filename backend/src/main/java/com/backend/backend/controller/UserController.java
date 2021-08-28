package com.backend.backend.controller;

import com.backend.backend.controller.response.UserResponse;
import com.backend.backend.repository.entity.MyRole;
import com.backend.backend.service.RoleService;
import com.backend.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(value = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    private ResponseEntity<List<UserResponse>> list() {
        List<UserResponse> list = new LinkedList<>();
        userService.list().forEach(user -> {
            UserResponse userResponse = user.transform();
            for (MyRole role: roleService.getByUserId(userResponse.getId())){
                userResponse.getRoles().add(role.getRol().name());
            }
            list.add(userResponse);
        });
        return ResponseEntity.ok(list);
    }

    @PostMapping("/logIn")
    public ResponseEntity<String> logIn(
            @RequestParam String username,
            @RequestParam String password
    ) {
        return ResponseEntity.ok(userService.logIn(username, password));
    }

    @PostMapping
    public ResponseEntity<Boolean> save(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String username,
            @RequestParam Integer dni
    ) {
        userService.save(name, surname, username, dni);
        return ResponseEntity.ok(true);
    }

    @PutMapping
    public ResponseEntity<Boolean> update(
            @RequestParam Integer id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String username,
            @RequestParam Integer dni
    ) {
        userService.update(id, name, surname, username, dni);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/roles")
    public ResponseEntity<Boolean> roles(
            @RequestParam Integer id,
            @RequestParam String[] roles
    ) {
        roleService.addRemoveRoleNameUserId(id,roles);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping
    private ResponseEntity<Boolean> delete(
            @RequestParam Integer[] ids
    ) {
        userService.delete(ids);
        return ResponseEntity.ok(true);
    }
}

