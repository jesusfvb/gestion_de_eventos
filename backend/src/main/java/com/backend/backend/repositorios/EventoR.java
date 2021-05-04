package com.backend.backend.repositorios;

import com.backend.backend.repositorios.entidades.Evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoR extends JpaRepository<Evento, Integer> {

}
