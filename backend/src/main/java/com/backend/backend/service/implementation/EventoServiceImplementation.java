package com.backend.backend.service.implementation;

import java.util.List;

import com.backend.backend.repository.EventoRepository;
import com.backend.backend.repository.entity.Evento;
import com.backend.backend.repository.entity.Evento.State;
import com.backend.backend.service.ConvocatoriaService;
import com.backend.backend.service.EventoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoServiceImplementation implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ConvocatoriaService convocatoriaService;

    @Override
    public List<Evento> getByIdConvocatoria(Integer id) {
        return eventoRepository.findByConvocatoriaId(id);
    }

    @Override
    public List<Evento> getByIdConvocatoriaAndState(Integer id, State state) {
        return eventoRepository.findByConvocatoriaIdAndState(id, state);
    }

    @Override
    public void save(Integer idConvocatoria, String name) {
        eventoRepository
                .save(new Evento(name, convocatoriaService.getConvocatoriaById(idConvocatoria), State.PENDIENTE));
    }

    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            eventoRepository.deleteById(id);
        }
    }

    @Override
    public void update(Integer id, String name) {
        Evento evento = eventoRepository.findById(id).get();
        evento.setName(name);
        eventoRepository.save(evento);
    }

}