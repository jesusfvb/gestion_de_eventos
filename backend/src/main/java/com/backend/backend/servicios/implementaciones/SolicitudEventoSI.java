package com.backend.backend.servicios.implementaciones;

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
    public void salvar(String nombreEvento, String clasificacion, String area) {
        repositorio.save(new SolicitudEvento(nombreEvento, clasificacion, area));
    }

}
