package com.backend.backend.repositorios.entidades;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.backend.backend.auxiliares.respuestas.ModPonencia;
import com.backend.backend.auxiliares.respuestas.ModSalaDePonencia;

@Entity
public class SalaDePonencia extends Entidad {

    @Column
    String nombre;

    @OneToMany
    List<Ponencia> ponencias;

    public SalaDePonencia() {
        this.ponencias= new LinkedList<>();
    }

    public SalaDePonencia(String nombre) {
        this.nombre = nombre;
        this.ponencias = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ponencia> getPonencias() {
        return ponencias;
    }

    public void setPonencias(List<Ponencia> ponencias) {
        this.ponencias = ponencias;
    }

    public ModSalaDePonencia convertir() {

        List<ModPonencia> lista = new LinkedList<>();
        for (Ponencia ponencia : ponencias) {
            lista.add(ponencia.convertir());
        }

        return new ModSalaDePonencia(super.getId(), nombre, lista);
    }

}
