package com.backend.backend.repositorios.entidades;

import javax.persistence.Entity;

import com.backend.backend.auxiliares.respuestas.ModPonencia;

@Entity
public class Ponencia extends Entidad {

    public Ponencia() {
    }

    public ModPonencia convertir() {
        return new ModPonencia(super.getId());
    }

}
