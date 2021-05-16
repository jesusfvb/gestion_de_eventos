package com.backend.backend.repositorios.entidades;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.backend.backend.auxiliares.respuestas.ModComision;
import com.backend.backend.auxiliares.respuestas.ModUsuario;

@Entity
public class Comision extends Entidad {

    @Column
    private String nombre;

    @Column
    private String lineaTematica;

    @OneToMany
    private List<Usuario> comiteOrganizador;

    @ManyToOne
    private Evento evento;

    @OneToMany
    private List<Usuario> miembros;

    public Comision() {
        this.miembros = new LinkedList<>();
    }

    public Comision(String nombre, String lineaTematica, List<Usuario> comiteOrganizador, Evento evento) {
        this.nombre = nombre;
        this.lineaTematica = lineaTematica;
        this.comiteOrganizador = comiteOrganizador;
        this.evento = evento;
        this.miembros = new LinkedList<>();
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

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Usuario> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Usuario> miembros) {
        this.miembros = miembros;
    }

    public ModComision convertir() {
        ModUsuario[] pivote = new ModUsuario[comiteOrganizador.size()];
        for (int i = 0; i < pivote.length; i++) {
            pivote[i] = comiteOrganizador.get(i).convertir();
        }
        return new ModComision(super.getId(), evento.convertir(), nombre, lineaTematica, pivote);
    }
}
