package com.backend.backend.auxiliares.respuestas;

import java.util.List;

import com.backend.backend.auxiliares.constantes.RolConst;

public class DetalleUsuario {

    private Integer id;

    private String nombre;

    private String apellido;

    private String usuario;

    private Integer carnetIdentidad;

    private List<RolConst> roles;

    public DetalleUsuario() {
    }

    public DetalleUsuario(Integer id, String nombre, String apellido, String usuario, Integer carnetIdentidad,
            List<RolConst> roles) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.carnetIdentidad = carnetIdentidad;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getCarnetIdentidad() {
        return carnetIdentidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public List<RolConst> getRoles() {
        return roles;
    }

}
