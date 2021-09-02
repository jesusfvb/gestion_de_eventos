package com.backend.backend.servicios;

import java.util.List;

import com.backend.backend.auxiliares.respuestas.ConvocatoriaResp;

import org.springframework.stereotype.Service;

@Service
public interface ConvocatoriaS {

    public List<ConvocatoriaResp> listarR();

    public Boolean salvar(String convocatoria);

    public Boolean modificar(Integer id, String convocatoria);

    public Boolean eliminar(Integer[] ids);
}
