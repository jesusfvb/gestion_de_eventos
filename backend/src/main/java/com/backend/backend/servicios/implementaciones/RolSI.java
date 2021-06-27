package com.backend.backend.servicios.implementaciones;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.auxiliares.constantes.RolEnum;
import com.backend.backend.repositorios.RolR;
import com.backend.backend.repositorios.entidades.Rol;
import com.backend.backend.repositorios.entidades.Usuario;
import com.backend.backend.servicios.RolS;
import com.backend.backend.servicios.UsuarioS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class RolSI implements RolS {

    @Autowired
    private RolR repositorio;

    @Autowired
    private UsuarioS servicioUsuario;

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

    @Override
    public List<RolEnum> listarLosDeUsuario(Integer id) {
        return servicioUsuario.getPorIdR(id).getRoles();
    }

    @Override
    public boolean gestionRol(Integer id, String[] roles) {
        Usuario usuario = servicioUsuario.getPorId(id);
        for (String rol : roles) {
            if (!existeEnListUsuario(usuario.getRoles(), rol)) {
                usuario.getRoles().add(comprobarSalvar(rol));
            }
        }
        if (roles.length < usuario.getRoles().size()) {
            int contador = 0;
            while (contador < usuario.getRoles().size()) {
                if (!existeEnArregloAdminRol(roles, usuario.getRoles().get(contador))) {
                    usuario.getRoles().remove(contador);
                } else {
                    contador++;
                }
            }
        }
        return servicioUsuario.salvar(usuario);
    }

    private Rol comprobarSalvar(String rol) {
        Rol pivote = new Rol(RolEnum.valueOf(rol));
        if (repositorio.exists(Example.of(pivote))) {
            return repositorio.findOne(Example.of(pivote)).get();
        }
        return repositorio.save(pivote);
    }

    private boolean existeEnListUsuario(List<Rol> lista, String rolString) {
        for (Rol pivote : lista) {
            if (rolString.equals(pivote.getRol().name())) {
                return true;
            }
        }
        return false;
    }

    private boolean existeEnArregloAdminRol(String[] roles, Rol rol) {
        for (String rolString : roles) {
            if (rolString.equals(rol.getRol().name())) {
                return true;
            }
        }
        return false;
    }
}
