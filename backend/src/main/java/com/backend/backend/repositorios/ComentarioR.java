package com.backend.backend.repositorios;

import com.backend.backend.repositorios.entidades.Comentario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioR extends JpaRepository<Comentario, Integer> {

}
