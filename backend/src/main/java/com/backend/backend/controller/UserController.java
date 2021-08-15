package com.backend.backend.controller;

import com.backend.backend.controller.response.UserResponse;
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
            List<String> roles = new LinkedList<>();
            roleService.getByUsername(user.getUsername()).forEach(myRole -> roles.add(myRole.getRol().name()));
            list.add(new UserResponse(user.getId(), user.getName(), user.getSurname(), user.getUsername(), user.getDni(), roles));
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

