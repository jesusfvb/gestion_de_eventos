package com.backend.backend.auxiliares.respuestas;

public class ModPonencia {

    private Integer id;

    private String nombre;

    private String nombreAutor;

    private Integer cantVotos;

    public ModPonencia(Integer id, String nombre, String nombreAutor, Integer cantVotos) {
        this.id = id;
        this.nombre = nombre;
        this.nombreAutor = nombreAutor;
        this.cantVotos = cantVotos;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public Integer getCantVotos() {
        return cantVotos;
    }
}
