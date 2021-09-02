package com.backend.backend.repositorios;

import com.backend.backend.repositorios.entidades.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface RolR extends JpaRepository<Rol, Integer> {

}
