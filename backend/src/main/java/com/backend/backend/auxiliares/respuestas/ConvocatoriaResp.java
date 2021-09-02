package com.backend.backend.auxiliares.respuestas;

public class ConvocatoriaResp {

    private Integer id;

    private String convocatoria;

    public ConvocatoriaResp() {
    }

    public ConvocatoriaResp(Integer id, String convocatoria) {
        this.id = id;
        this.convocatoria = convocatoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

}
