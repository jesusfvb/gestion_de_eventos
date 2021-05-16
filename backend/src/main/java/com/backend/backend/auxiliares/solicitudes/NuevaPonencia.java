package com.backend.backend.auxiliares.solicitudes;

import java.io.File;

public class NuevaPonencia {

    private Integer idAutor;

    private String nombre;

    private File archivo;

    private Integer[] idsCoautores;

    public NuevaPonencia() {
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public File getArchivo() {
        return archivo;
    }

    public Integer[] getIdsCoautores() {
        return idsCoautores;
    }
}
