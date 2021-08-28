package com.backend.backend.repository.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class MyRole extends MyEntity {
    public enum Role {USER, ADMINISTRATION,COORDINADOR}

    @Column
    private Role rol;

    @ManyToMany()
    private List<MyUser> users;

    public MyRole() {
    }

    public MyRole(Role rol) {
        this.rol = rol;
        this.users = new LinkedList<>();
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public List<MyUser> getUsers() {
        return users;
    }

    public void setUsers(List<MyUser> users) {
        this.users = users;
    }
}
