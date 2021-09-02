package com.backend.backend.repositorios;

import java.util.List;

import com.backend.backend.repositorios.entidades.Ponencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PonenciaR extends JpaRepository<Ponencia, Integer> {

    @Query("select p from Ponencia p where p.autor.id=?1")
    public List<Ponencia> getAllPorIdAutor(Integer id);

    @Query("select p from Ponencia p where p.revisor.id=?1")
    public List<Ponencia> getAllPorIdRevisor(Integer id);

}
