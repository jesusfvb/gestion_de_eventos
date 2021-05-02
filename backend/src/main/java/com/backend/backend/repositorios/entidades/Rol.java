package com.backend.backend.repositorios.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.backend.backend.auxiliares.constantes.RolConst;

@Entity
public class Rol extends Entidad {
    
    @Column
    private RolConst rol;

    public Rol() {
    }

    public Rol(RolConst rol) {
        this.rol = rol;
    }

    public RolConst getRol() {
        return rol;
    }

    public void setRol(RolConst rol) {
        this.rol = rol;
    }

    
}
