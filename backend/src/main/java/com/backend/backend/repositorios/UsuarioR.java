package com.backend.backend.repositorios;

import com.backend.backend.repositorios.entidades.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioR extends JpaRepository<Usuario, Integer> {

}
