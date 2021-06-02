package com.backend.backend.servicios;

import java.util.List;

import com.backend.backend.repositorios.entidades.Rol;

import org.springframework.stereotype.Service;

@Service
public interface RolS {

    public List<Rol> getRolesIniciales();

}
