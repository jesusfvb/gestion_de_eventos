package com.backend.backend.service;

import java.util.List;

import com.backend.backend.repository.entity.Evento;
import com.backend.backend.repository.entity.Evento.State;

import org.springframework.stereotype.Service;

@Service
public interface EventoService {

    public List<Evento> getByIdConvocatoria(Integer id);

    public List<Evento> getByIdConvocatoriaAndState(Integer id, State state);

    public void save(Integer idConvocatoria, String name);

    public void delete(Integer[] ids);

    public void update(Integer id, String name);

}
