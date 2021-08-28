package com.backend.backend.controller.response;

import java.util.LinkedList;
import java.util.List;

public class UserResponse {

    private final Integer id;

    private final String name;

    private final String surname;

    private final String username;

    private final Integer dni;

    private final List<String> roles;

    public UserResponse(Integer id, String name, String surname, String username, Integer dni) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.dni = dni;
        this.roles = new LinkedList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public Integer getDni() {
        return dni;
    }

    public List<String> getRoles() {
        return roles;
    }
}
