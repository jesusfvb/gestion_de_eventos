package com.backend.backend.repository.entity;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class MyUser extends MyEntity {

    @Column
    private String username;

    @Column
    private String password;

    @OneToMany
    private List<MyRole> roles;

    public MyUser() {
    }

    public MyUser(String username, String password, List<MyRole> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
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

    public List<MyRole> getRoles() {
        return roles;
    }

    public void setRoles(List<MyRole> roles) {
        this.roles = roles;
    }
}
