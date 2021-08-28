package com.backend.backend.repository.entity;

import com.backend.backend.controller.response.ConvocatoriaResponse;
import com.backend.backend.controller.response.UserResponse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Convocatoria extends MyEntity {

    @Column
    private String text;

    @ManyToOne
    private MyUser convocatoriaBoss;

    public Convocatoria() {
    }

    public Convocatoria(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MyUser getConvocatoriaBoss() {
        return convocatoriaBoss;
    }

    public void setConvocatoriaBoss(MyUser convocatoriaBoss) {
        this.convocatoriaBoss = convocatoriaBoss;
    }

    public ConvocatoriaResponse transform() {
        if (this.convocatoriaBoss == null) {
            return new ConvocatoriaResponse(super.getId(), this.text, null);
        }
        return new ConvocatoriaResponse(super.getId(), this.text, this.convocatoriaBoss.transform());
    }
}
