package com.backend.backend.repositorios.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.backend.backend.auxiliares.constantes.RolEnum;

@Entity
public class Rol extends Entidad {
    
    @Column
    private RolEnum rol;

    public Rol() {
    }

    public Rol(RolEnum rol) {
        this.rol = rol;
    }

    public RolEnum getRol() {
        return rol;
    }

    public void setRol(RolEnum rol) {
        this.rol = rol;
    }

    
}
