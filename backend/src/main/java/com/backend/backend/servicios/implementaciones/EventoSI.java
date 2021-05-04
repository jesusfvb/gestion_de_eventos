package com.backend.backend.servicios.implementaciones;

import java.time.LocalDate;
import java.util.List;

import com.backend.backend.repositorios.EventoR;
import com.backend.backend.repositorios.entidades.Evento;
import com.backend.backend.servicios.EventoS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoSI implements EventoS {

    @Autowired
    private EventoR repositorio;

    @Override
    public List<Evento> listar() {
        return repositorio.findAll();
    }

    @Override
    public void salvar(String nombre, String area, String clasificacion, String edicion, LocalDate inicio,
            LocalDate fin) {
        repositorio.save(new Evento(nombre, area, clasificacion, edicion, inicio, fin, ""));
    }

    @Override
    public void eliminar(Integer[] ids) {
        for (Integer id : ids) {
            repositorio.deleteById(id);
        }
    }
}
