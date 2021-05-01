package com.backend.backend.auxiliares.respuestas;

import java.util.List;

import com.backend.backend.auxiliares.constantes.RolConst;

public class ListarUsuario {

    private String nombre;

    private String apellido;

    private String usuario;

    private List<RolConst> roles;

    public ListarUsuario() {
    }


    public ListarUsuario(String nombre, String apellido, String usuario, List<RolConst> roles) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.roles = roles;
    }


    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public List<RolConst> getRoles() {
        return roles;
    }

}
