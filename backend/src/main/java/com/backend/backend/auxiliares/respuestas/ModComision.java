package com.backend.backend.auxiliares.respuestas;

public class ModComision {

    private Integer id;

    private ModEvento evento;

    private String nombre;

    private String lineaTematica;

    private UsuarioResp[] comiteOrganizador;

    public ModComision() {
    }

    public ModComision(Integer id, ModEvento evento, String nombre, String lineaTematica,
            UsuarioResp[] comiteOrganizador) {
        this.id = id;
        this.evento = evento;
        this.nombre = nombre;
        this.lineaTematica = lineaTematica;
        this.comiteOrganizador = comiteOrganizador;
    }

    public Integer getId() {
        return id;
    }

    public ModEvento getEvento() {
        return evento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLineaTematica() {
        return lineaTematica;
    }

    public UsuarioResp[] getComiteOrganizador() {
        return comiteOrganizador;
    }
}
