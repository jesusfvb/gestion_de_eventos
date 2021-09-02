package com.backend.backend.repositorios;

import com.backend.backend.repositorios.entidades.SalaDePonencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaDePonenciaR extends JpaRepository<SalaDePonencia, Integer> {

}
