package com.backend.backend.auxiliares.solicitudes;

public class NuevaComision {

    private Integer idEvento;

    private String nombre;

    private String lineaTematica;

    private Integer[] idsComiteOrganizador;

    public NuevaComision() {
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLineaTematica() {
        return lineaTematica;
    }

    public Integer[] getIdsComiteOrganizador() {
        return idsComiteOrganizador;
    }
}
