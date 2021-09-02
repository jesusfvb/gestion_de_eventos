package com.backend.backend.auxiliares.respuestas;

public class ArchivoResp {
    private Integer id;

    private String url;

    public ArchivoResp() {

    }

    public ArchivoResp(Integer id, String url) {
        this.id = id;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

}
