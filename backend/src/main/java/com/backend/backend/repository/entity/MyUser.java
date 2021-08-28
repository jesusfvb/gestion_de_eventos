package com.backend.backend.repository.entity;

import com.backend.backend.controller.response.UserResponse;
import com.backend.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.LinkedList;
import java.util.List;

@Entity
public class MyUser extends MyEntity {

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Integer dni;

    public MyUser() {
    }

    public MyUser(String name, String surname, String username, String password, Integer dni) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public UserResponse transform() {
        return new UserResponse(super.getId(), this.name, this.surname, this.username, this.dni);
    }

}
