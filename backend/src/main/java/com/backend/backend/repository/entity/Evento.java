package com.backend.backend.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.backend.backend.controller.response.EventoResponse;

@Entity
public class Evento extends MyEntity {

    public enum State {
        PENDIENTE, APROBADO, DESAPROBAD,
    };

    @Column
    private String name;

    @Column
    private State state;

    @ManyToOne
    private Convocatoria convocatoria;

    public Evento() {
    }

    public Evento(String name, Convocatoria convocatoria, State state) {
        this.name = name;
        this.convocatoria = convocatoria;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Convocatoria getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatoria convocatoria) {
        this.convocatoria = convocatoria;
    }

    public EventoResponse transform() {
        return new EventoResponse(super.getId(), this.name,this.state.name());
    }
}
