package com.backend.backend.servicios.implementaciones;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.repositorios.ComisionR;
import com.backend.backend.repositorios.entidades.Comision;
import com.backend.backend.repositorios.entidades.Usuario;
import com.backend.backend.servicios.ComisionS;
import com.backend.backend.servicios.EventoS;
import com.backend.backend.servicios.UsuarioS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComisionSI implements ComisionS {

    @Autowired
    private ComisionR repositorio;

    @Autowired
    private EventoS servicioEvento;

    @Autowired
    private UsuarioS serviceUsuario;

    @Override
    public List<Comision> listar() {
        return repositorio.findAll();
    }

    @Override
    public void salvar(Integer idEvento, String nombre, String lineTematica, Integer[] idsComiteOrganizador) {
        List<Usuario> comiteOrganizador = new LinkedList<>();
        for (Integer id : idsComiteOrganizador) {
            comiteOrganizador.add(serviceUsuario.getPorId(id));
        }
        repositorio.save(new Comision(nombre, lineTematica, comiteOrganizador, servicioEvento.getPorId(idEvento)));
    }

}
