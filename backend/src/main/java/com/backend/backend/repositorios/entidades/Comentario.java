package com.backend.backend.repositorios.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.backend.backend.auxiliares.respuestas.ModComentario;

@Entity
public class Comentario extends Entidad {

    @ManyToOne
    private Usuario usuario;

    @Column
    private String comentario;

    public Comentario() {
    }

    public Comentario(Usuario usuario, String comentario) {
        this.usuario = usuario;
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public ModComentario convertir() {
        return new ModComentario(super.getId(), usuario.getUsuario(), comentario);
    }
}
