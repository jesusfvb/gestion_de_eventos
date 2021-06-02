package com.backend.backend.repositorios.entidades;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.backend.backend.auxiliares.constantes.RolEnum;
import com.backend.backend.auxiliares.respuestas.UsuarioResp;

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
    private long carnetIdentidad;

    @ManyToMany
    private List<Rol> roles;

    @OneToOne
    private Archivo foto;

    public Usuario() {
        this.roles = new LinkedList<>();
    }

    public Usuario(String nombre, String apellido, String usuario, String contrasenna, long carnetIdentidad,
            List<Rol> roles, Archivo foto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasenna = contrasenna;
        this.carnetIdentidad = carnetIdentidad;
        this.roles = roles;
        this.foto = foto;
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

    public long getCarnetIdentidad() {
        return carnetIdentidad;
    }

    public void setCarnetIdentidad(long carnetIdentidad) {
        this.carnetIdentidad = carnetIdentidad;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Archivo getFoto() {
        return foto;
    }

    public void setFoto(Archivo foto) {
        this.foto = foto;
    }

    public UsuarioResp convertir() {
        List<RolEnum> pivote = new LinkedList<>();
        roles.forEach(rol -> {
            pivote.add(rol.getRol());
        });

        return new UsuarioResp(super.getId(), nombre, apellido, usuario, carnetIdentidad, pivote,
                (foto == null ? null : foto.convertir()));
    }
}
