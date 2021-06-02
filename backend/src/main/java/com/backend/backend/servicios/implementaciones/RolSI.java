package com.backend.backend.servicios.implementaciones;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.auxiliares.constantes.RolEnum;
import com.backend.backend.repositorios.RolR;
import com.backend.backend.repositorios.entidades.Rol;
import com.backend.backend.servicios.RolS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class RolSI implements RolS {

    @Autowired
    private RolR repositorio;

    @Override
    public List<Rol> getRolesIniciales() {
        List<Rol> lista = new LinkedList<>();
        if (repositorio.exists(Example.of(new Rol(RolEnum.USUARIO)))) {
            lista.add(repositorio.findOne(Example.of(new Rol(RolEnum.USUARIO))).get());
        } else {
            lista.add(repositorio.save(new Rol(RolEnum.USUARIO)));
        }
        return lista;
    }

}
