package com.backend.backend.repositorios;

import com.backend.backend.repositorios.entidades.SolicitudEvento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudEventoR extends JpaRepository<SolicitudEvento, Integer> {

}
