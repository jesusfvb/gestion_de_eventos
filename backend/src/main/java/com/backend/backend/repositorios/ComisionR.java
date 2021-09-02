package com.backend.backend.repositorios;

import com.backend.backend.repositorios.entidades.Comision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComisionR extends JpaRepository<Comision, Integer> {

}
