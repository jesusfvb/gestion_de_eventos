package com.backend.backend.auxiliares.solicitudes;

public class SolicitarEvento {

    private String nombre;

    private String clasificacion;

    private String area;

    public SolicitarEvento() {
    }

    public SolicitarEvento(String nombre, String clasificacion, String area) {
        this.nombre = nombre;
        this.clasificacion = clasificacion;
        this.area = area;
    }

    public String getNombre() {
        return nombre;
    }

    public String getArea() {
        return area;
    }

    public String getClasificacion() {
        return clasificacion;
    }
}
