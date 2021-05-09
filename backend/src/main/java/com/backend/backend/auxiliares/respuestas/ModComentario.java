package com.backend.backend.auxiliares.respuestas;

public class ModComentario {

    private Integer id;

    private String nombre;

    private String comentario;

    public ModComentario() {
    }

    public ModComentario(Integer id, String nombre, String comentario) {
        this.id = id;
        this.nombre = nombre;
        this.comentario = comentario;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getComentario() {
        return comentario;
    }
}
