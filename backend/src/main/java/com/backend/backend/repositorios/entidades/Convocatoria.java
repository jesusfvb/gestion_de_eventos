package com.backend.backend.repositorios.entidades;

import javax.persistence.Entity;

import com.backend.backend.auxiliares.respuestas.ConvocatoriaResp;

@Entity
public class Convocatoria extends Entidad {

    private String convocatoria;

    public Convocatoria() {
    }

    public Convocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    public String getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    public ConvocatoriaResp convertir() {
        return new ConvocatoriaResp(super.getId(), convocatoria);
    }
}
