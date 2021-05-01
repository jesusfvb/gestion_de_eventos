package com.backend.backend.servicios.implementaciones;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.auxiliares.constantes.RolConst;
import com.backend.backend.repositorios.RolR;
import com.backend.backend.repositorios.UsuarioR;
import com.backend.backend.repositorios.entidades.Rol;
import com.backend.backend.repositorios.entidades.Usuario;
import com.backend.backend.servicios.UsuarioS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class UsuarioSI implements UsuarioS {

    @Autowired
    private UsuarioR repositorio;

    @Autowired
    private RolR repositorioRol;

    @Override
    public List<Usuario> listar() {
        return repositorio.findAll();
    }

    @Override
    public void salvar(String nombre, String apellido, String usuario, Integer carnetIdentidad) {
        List<Rol> roles = new LinkedList<>();
        roles.add(comprobar_anadir_Rol(RolConst.USUARIO));
        repositorio.save(new Usuario(nombre, apellido, usuario, usuario, carnetIdentidad, roles));
    }

    @Override
    public void eliminar(Integer[] ids) {
       for (Integer id : ids) {
           repositorio.deleteById(id);
       }
    }

    private Rol comprobar_anadir_Rol(RolConst rol) {
        Rol salida;
        if (repositorioRol.exists(Example.of(new Rol(rol)))) {
            salida = repositorioRol.findOne(Example.of(new Rol(rol))).get();
        } else {
            salida = repositorioRol.save(new Rol(rol));
        }
        return salida;
    }

}
