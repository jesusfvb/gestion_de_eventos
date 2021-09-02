package com.backend.backend.servicios.implementaciones;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.auxiliares.respuestas.SolicitudEventoResp;
import com.backend.backend.repositorios.SolicitudEventoR;
import com.backend.backend.repositorios.entidades.SolicitudEvento;
import com.backend.backend.servicios.SolicitudEventoS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitudEventoSI implements SolicitudEventoS {

    @Autowired
    private SolicitudEventoR repositorio;

    @Override
    public List<SolicitudEventoResp> listar() {
        List<SolicitudEventoResp> respuesta = new LinkedList<>();
        repositorio.findAll().forEach(solicitudEvento -> {
            respuesta.add(solicitudEvento.convertir());
        });
        return respuesta;
    }

    @Override
    public void salvar(String nombreEvento, String clasificacion, String area) {
        repositorio.save(new SolicitudEvento(nombreEvento, clasificacion, area));
    }

}
