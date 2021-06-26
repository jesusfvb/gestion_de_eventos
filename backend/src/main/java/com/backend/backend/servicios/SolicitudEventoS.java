package com.backend.backend.servicios;

import org.springframework.stereotype.Service;

@Service
public interface SolicitudEventoS {

    public void salvar(String nombreEvento, String clasificacion, String area);

}
