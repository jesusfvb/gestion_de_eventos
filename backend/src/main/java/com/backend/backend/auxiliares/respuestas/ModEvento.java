package com.backend.backend.auxiliares.respuestas;

public class ModEvento {
    private Integer id;

    private String nombre;

    private String area;

    private String clasificacion;

    private String edicion;

    private String inicio;

    private String fin;

    public ModEvento() {
    }

    

    public ModEvento(Integer id, String nombre, String area, String clasificacion, String edicion, String inicio,
            String fin) {
        this.id = id;
        this.nombre = nombre;
        this.area = area;
        this.clasificacion = clasificacion;
        this.edicion = edicion;
        this.inicio = inicio;
        this.fin = fin;
    }



    public Integer getId() {
        return id;
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

    public String getInicio() {
        return inicio;
    }

    public String getFin() {
        return fin;
    }
}
