package com.backend.backend.servicios;

import java.util.List;

import com.backend.backend.auxiliares.respuestas.SolicitudEventoResp;

import org.springframework.stereotype.Service;

@Service
public interface SolicitudEventoS {

    public List<SolicitudEventoResp> listar();

    public void salvar(String nombreEvento, String clasificacion, String area);

}
