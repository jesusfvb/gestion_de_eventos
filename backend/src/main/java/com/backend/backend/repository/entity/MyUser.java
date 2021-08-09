package com.backend.backend.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class MyUser extends MyEntity{

    @Column
    private String username;

    @Column
    private String password;

    public MyUser() {
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
}
