package com.backend.backend.servicios;

import java.util.List;

import com.backend.backend.repositorios.entidades.Comision;

import org.springframework.stereotype.Service;

@Service
public interface ComisionS {

    public List<Comision> listar();

    public Comision getPorId(Integer id);

    public void salvar(Integer idEvento, String nombre, String lineTematica, Integer[] idsComiteOrganizador);

    public void modificar(Integer id, String parametro, Object nuevoValor);

    public void borrar(Integer[] ids);

}
