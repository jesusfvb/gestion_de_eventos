package com.backend.backend.auxiliares.respuestas;

public class SolicitudEventoResp {

    private Integer id;

    private String nombreEvento;

    private String clasificacion;

    private String area;

    public SolicitudEventoResp() {

    }

    public SolicitudEventoResp(Integer id, String nombreEvento, String clasificacion, String area) {
        this.id = id;
        this.nombreEvento = nombreEvento;
        this.clasificacion = clasificacion;
        this.area = area;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
