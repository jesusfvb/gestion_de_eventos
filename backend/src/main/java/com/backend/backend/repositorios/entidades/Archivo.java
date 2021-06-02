package com.backend.backend.repositorios.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import com.backend.backend.auxiliares.respuestas.ArchivoResp;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Entity
public class Archivo extends Entidad {

    @Column
    private String name;

    @Column
    private String contentType;

    @Column
    private Long size;

    @Lob
    private byte[] data;

    public Archivo() {

    }

    public Archivo(String name, String contentType, Long size, byte[] data) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public ArchivoResp convertir() {
        return new ArchivoResp(super.getId(), ServletUriComponentsBuilder.fromCurrentContextPath().path("/archivo/")
                .path(super.getId().toString()).toUriString());
    }
}
