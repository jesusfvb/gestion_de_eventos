package com.backend.backend.servicios;

import java.time.LocalDate;
import java.util.List;

import com.backend.backend.repositorios.entidades.Evento;

import org.springframework.stereotype.Service;

@Service
public interface EventoS {

    public List<Evento> listar();

    public void salvar(String nombre, String area, String clasificacion, String edicion, LocalDate inicio,
            LocalDate fin);

    public void eliminar(Integer[] ids);
}
