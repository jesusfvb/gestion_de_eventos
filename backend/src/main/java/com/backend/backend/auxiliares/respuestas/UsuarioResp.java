package com.backend.backend.auxiliares.respuestas;

import java.util.List;

import com.backend.backend.auxiliares.constantes.RolEnum;

public class UsuarioResp {

    private Integer id;

    private String nombre;

    private String apellido;

    private String usuario;

    private long carnetIdentidad;

    private ArchivoResp foto;

    private List<RolEnum> roles;

    public UsuarioResp() {
    }

    public UsuarioResp(Integer id, String nombre, String apellido, String usuario, long carnetIdentidad,
            List<RolEnum> roles, ArchivoResp foto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.carnetIdentidad = carnetIdentidad;
        this.roles = roles;
        this.foto = foto;
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

    public long getCarnetIdentidad() {
        return carnetIdentidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public List<RolEnum> getRoles() {
        return roles;
    }

    public ArchivoResp getFoto() {
        return foto;
    }

}
