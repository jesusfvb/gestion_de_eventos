package com.backend.backend.repositorios.entidades;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.backend.backend.auxiliares.respuestas.ModEvento;

@Entity
public class Evento extends Entidad {

    @Column
    private String nombre;

    @Column
    private String area;

    @Column
    private String clasificacion;

    @Column
    private String edicion;

    @Column
    private LocalDate inicio;

    @Column
    private LocalDate fin;

    @Column
    private String convocatoria;

    @Column
    private Boolean aprobacion;

    public Evento() {
    }

    public Evento(String nombre, String area, String clasificacion, String edicion, LocalDate inicio, LocalDate fin,
            String convocatoria) {
        this.nombre = nombre;
        this.area = area;
        this.clasificacion = clasificacion;
        this.edicion = edicion;
        this.inicio = inicio;
        this.fin = fin;
        this.convocatoria = convocatoria;
        this.aprobacion = true;
    }

    public Evento(String nombre, String area, String clasificacion) {
        this.nombre = nombre;
        this.area = area;
        this.clasificacion = clasificacion;
        this.edicion = null;
        this.inicio = null;
        this.fin = null;
        this.convocatoria = null;
        this.aprobacion = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public String getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    public Boolean getAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(Boolean aprobacion) {
        this.aprobacion = aprobacion;
    }

    public ModEvento convertir() {
        return new ModEvento(super.getId(), nombre, area, clasificacion, edicion != null ? edicion : "",
                inicio != null ? inicio.toString() : "", fin != null ? fin.toString() : "");
    }
}
