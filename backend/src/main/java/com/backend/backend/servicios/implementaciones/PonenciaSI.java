package com.backend.backend.servicios.implementaciones;

import java.util.List;

import com.backend.backend.repositorios.PonenciaR;
import com.backend.backend.repositorios.SalaDePonenciaR;
import com.backend.backend.repositorios.entidades.SalaDePonencia;
import com.backend.backend.servicios.EventoS;
import com.backend.backend.servicios.PonenciaS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PonenciaSI implements PonenciaS {

    @Autowired
    private PonenciaR repositorio;

    @Autowired
    private SalaDePonenciaR repositorioSala;

    @Autowired
    private EventoS servicioEvento;

    @Override
    public List<SalaDePonencia> listarSalaDePonencia(Integer id) {
        return servicioEvento.getPorId(id).getSalasDePonencia();
    }

    @Override
    public void nuevaSalaDePonencia(Integer id, String nombre) {
        servicioEvento.agregarSalaDePonencia(id, repositorioSala.save(new SalaDePonencia(nombre)));
    }

    @Override
    public void eliminarSalaDePonencia(Integer id, Integer idEvento) {
        servicioEvento.removerSalaDePonencia(idEvento, id);
        repositorioSala.deleteById(id);
    }

}
