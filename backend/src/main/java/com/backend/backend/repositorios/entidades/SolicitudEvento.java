package com.backend.backend.repositorios.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SolicitudEvento extends Entidad {

    @Column
    private String nombreEvento;

    @Column
    private String clasificacion;

    @Column
    private String area;

    public SolicitudEvento() {
    }

    public SolicitudEvento(String nombreEvento, String clasificacion, String area) {
        this.nombreEvento = nombreEvento;
        this.clasificacion = clasificacion;
        this.area = area;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
