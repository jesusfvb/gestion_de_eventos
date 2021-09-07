package com.backend.backend.repository;

import java.util.List;

import com.backend.backend.repository.entity.Evento;
import com.backend.backend.repository.entity.Evento.State;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    List<Evento> findByConvocatoriaId(Integer id);

    List<Evento> findByConvocatoriaIdAndState(Integer id, State state);

}
