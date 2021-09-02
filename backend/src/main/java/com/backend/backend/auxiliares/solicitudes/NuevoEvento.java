package com.backend.backend.auxiliares.solicitudes;

import java.time.LocalDate;

public class NuevoEvento {

    private String nombre;

    private String area;

    private String clasificacion;

    private String edicion;

    private LocalDate inicio;

    private LocalDate fin;

    public NuevoEvento() {
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

    public String getEdicion() {
        return edicion;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

}
