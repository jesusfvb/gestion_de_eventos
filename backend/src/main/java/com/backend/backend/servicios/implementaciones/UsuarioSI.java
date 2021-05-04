package com.backend.backend.servicios.implementaciones;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.auxiliares.constantes.RolEnum;
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
        roles.add(comprobar_anadir_Rol(RolEnum.USUARIO));
        repositorio.save(new Usuario(nombre, apellido, usuario, usuario, carnetIdentidad, roles));
    }

    @Override
    public void eliminar(Integer[] ids) {
        for (Integer id : ids) {
            repositorio.deleteById(id);
        }
    }

    @Override
    public void modificar(Integer id, String parametro, Object nuevoValor) {
        Usuario usuario = getPorId(id);
        switch (parametro) {
            case "nombre":
                usuario.setNombre((String) nuevoValor);
                break;
            case "apellido":
                usuario.setApellido((String) nuevoValor);
                break;
            case "usuario":
                usuario.setUsuario((String) nuevoValor);
                break;
            case "contraseña":
                usuario.setContrasenna((String) nuevoValor);
                break;
            case "carnetIdentidad":
                usuario.setCarnetIdentidad((Integer) nuevoValor);
                break;
        }
    }

    @Override
    public Usuario getPorId(Integer id) {
        return repositorio.findById(id).get();
    }

    @Override
    public List<Rol> getLisRolesPorId(Integer id) {
        return getPorId(id).getRoles();
    }

    @Override
    public void adjuntarRol(Integer id, RolEnum rol) {
        Usuario pivote = getPorId(id);
        pivote.getRoles().add(comprobar_anadir_Rol(rol));
        repositorio.save(pivote);
    }

    @Override
    public void removerRol(Integer id, RolEnum rol) {
        Usuario pivote = getPorId(id);
        pivote.getRoles().removeIf(role -> role.getRol().equals(rol) == true);
        repositorio.save(pivote);
    }

    private Rol comprobar_anadir_Rol(RolEnum rol) {
        Rol salida;
        if (repositorioRol.exists(Example.of(new Rol(rol)))) {
            salida = repositorioRol.findOne(Example.of(new Rol(rol))).get();
        } else {
            salida = repositorioRol.save(new Rol(rol));
        }
        return salida;
    }

}
