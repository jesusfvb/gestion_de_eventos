package com.backend.backend.repositorios.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Comision extends Entidad {

    @Column
    private String nombre;

    @Column
    private String lineaTematica;

    @OneToMany
    private List<Usuario> comiteOrganizador;

    public Comision() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLineaTematica() {
        return lineaTematica;
    }

    public void setLineaTematica(String lineaTematica) {
        this.lineaTematica = lineaTematica;
    }

    public List<Usuario> getComiteOrganizador() {
        return comiteOrganizador;
    }

    public void setComiteOrganizador(List<Usuario> comiteOrganizador) {
        this.comiteOrganizador = comiteOrganizador;
    }

    
}
