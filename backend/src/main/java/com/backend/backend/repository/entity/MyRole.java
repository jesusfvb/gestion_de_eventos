package com.backend.backend.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class MyRole extends MyEntity {
    public enum Role {USER, ADMINISTRATION}

    @Column
    private Role rol;

    public MyRole() {
    }

    public MyRole(Role rol) {
        this.rol = rol;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }
}
