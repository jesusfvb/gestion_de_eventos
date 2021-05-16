package com.backend.backend.auxiliares.respuestas;

import java.util.List;

public class ModSalaDePonencia {

    private Integer id;

    private String nombre;

    private List<ModPonencia> ponencias;

    public ModSalaDePonencia(Integer id, String nombre, List<ModPonencia> ponencias) {
        this.id = id;
        this.nombre = nombre;
        this.ponencias = ponencias;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<ModPonencia> getPonencias() {
        return ponencias;
    }

}
