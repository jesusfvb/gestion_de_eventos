package com.backend.backend.servicios;

import java.util.List;

import com.backend.backend.repositorios.entidades.SalaDePonencia;

import org.springframework.stereotype.Service;

@Service
public interface PonenciaS {

    public List<SalaDePonencia> listarSalaDePonencia(Integer id);

    public void nuevaSalaDePonencia(Integer id, String nombre);    

    public void eliminarSalaDePonencia(Integer id, Integer idEvento);
}
