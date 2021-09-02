package com.backend.backend.repositorios;

import com.backend.backend.repositorios.entidades.Convocatoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvocatoriaR extends JpaRepository<Convocatoria, Integer> {

}
