package com.backend.backend.servicios.implementaciones;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.auxiliares.respuestas.ConvocatoriaResp;
import com.backend.backend.repositorios.ConvocatoriaR;
import com.backend.backend.repositorios.entidades.Convocatoria;
import com.backend.backend.servicios.ConvocatoriaS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvocatoriaSI implements ConvocatoriaS {

    @Autowired
    private ConvocatoriaR repositorio;

    @Override
    public List<ConvocatoriaResp> listarR() {
        List<ConvocatoriaResp> salida = new LinkedList<>();
        repositorio.findAll().forEach(convocatoria -> {
            salida.add(convocatoria.convertir());
        });
        return salida;
    }

    @Override
    public Boolean salvar(String convocatoria) {
        return repositorio.save(new Convocatoria(convocatoria)) != null;
    }

    @Override
    public Boolean modificar(Integer id, String convocatoria) {
        Convocatoria salida = repositorio.findById(id).get();
        if (salida.getConvocatoria().equals(convocatoria) == false) {
            salida.setConvocatoria(convocatoria);
            return repositorio.save(salida) != null;
        } else {
            return false;
        }
    }

    @Override
    public Boolean eliminar(Integer[] ids) {
        for (Integer id : ids) {
            repositorio.deleteById(id);
        }
        return true;
    }
}
