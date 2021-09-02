package com.backend.backend.repositorios;

import com.backend.backend.repositorios.entidades.Archivo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoR extends JpaRepository<Archivo, Integer> {

}
