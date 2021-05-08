package com.backend.backend.repositorios;

import com.backend.backend.repositorios.entidades.Ponencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PonenciaR extends JpaRepository<Ponencia, Integer> {

}
