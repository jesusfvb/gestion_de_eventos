package com.backend.backend.servicios;

import java.util.List;

import com.backend.backend.repositorios.entidades.Comision;

import org.springframework.stereotype.Service;

@Service
public interface ComisionS {

    public List<Comision> listar();

    public void salvar(Integer idEvento, String nombre, String lineTematica, Integer[] idsComiteOrganizador);

}
