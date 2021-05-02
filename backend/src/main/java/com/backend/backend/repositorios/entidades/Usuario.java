package com.backend.backend.repositorios.entidades;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.backend.backend.auxiliares.constantes.RolConst;
import com.backend.backend.auxiliares.respuestas.DetalleUsuario;

@Entity
public class Usuario extends Entidad {

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String usuario;

    @Column
    private String contrasenna;

    @Column
    private Integer carnetIdentidad;

    @ManyToMany
    private List<Rol> roles;

    public Usuario() {
        this.roles = new LinkedList<>();
    }

    public Usuario(String nombre, String apellido, String usuario, String contrasenna, Integer carnetIdentidad,
            List<Rol> roles) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasenna = contrasenna;
        this.carnetIdentidad = carnetIdentidad;
        this.roles = roles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public Integer getCarnetIdentidad() {
        return carnetIdentidad;
    }

    public void setCarnetIdentidad(Integer carnetIdentidad) {
        this.carnetIdentidad = carnetIdentidad;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public DetalleUsuario convertir() {
        List<RolConst> pivote = new LinkedList<>();
        roles.forEach(rol -> {
            pivote.add(rol.getRol());
        });
        return new DetalleUsuario(super.getId(), nombre, apellido, usuario, carnetIdentidad, pivote);
    }
}
