package com.backend.backend.repositorios.entidades;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.backend.backend.auxiliares.respuestas.ModPonencia;

@Entity
public class Ponencia extends Entidad {

    @OneToOne
    private Usuario autor;

    @Column
    private String nombre;

    @Column
    private File archivo;

    @ManyToMany
    private List<Usuario> coautores;

    @ManyToMany
    private List<Comentario> comentarios;

    public Ponencia() {
        this.comentarios = new LinkedList<>();
    }

    public Ponencia(Usuario autor, String nombre, File archivo, List<Usuario> coautores) {
        this.autor = autor;
        this.nombre = nombre;
        this.archivo = archivo;
        this.coautores = coautores;
        this.comentarios = new LinkedList<>();
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public List<Usuario> getCoautores() {
        return coautores;
    }

    public void setCoautores(List<Usuario> coautores) {
        this.coautores = coautores;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public ModPonencia convertir() {
        return new ModPonencia(super.getId());
    }

}
