package com.backend.backend.servicios.implementaciones;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.auxiliares.respuestas.UsuarioResp;
import com.backend.backend.repositorios.UsuarioR;
import com.backend.backend.repositorios.entidades.Usuario;
import com.backend.backend.servicios.ArchivoS;
import com.backend.backend.servicios.RolS;
import com.backend.backend.servicios.UsuarioS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioSI implements UsuarioS {

    @Autowired
    private UsuarioR repositorio;

    @Autowired
    private RolS serviciosRol;

    @Autowired
    private ArchivoS serviciosArchivo;

    @Override
    public List<UsuarioResp> listarR() {
        List<UsuarioResp> lista = new LinkedList<>();
        repositorio.findAll().forEach(usuario -> {
            lista.add(usuario.convertir());
        });
        return lista;
    }

    @Override
    public void salvar(String nombre, String apellido, String usuario, long carnetIdentidad, MultipartFile foto) {
        repositorio.save(new Usuario(nombre, apellido, usuario, "", carnetIdentidad, serviciosRol.getRolesIniciales(),
                serviciosArchivo.save(foto)));

    }

    @Override
    public boolean salvar(Usuario usuario) {
        repositorio.save(usuario);
        return true;
    }

    @Override
    public void eliminar(Integer[] ids) {
        for (Integer id : ids) {
            Integer[] idFoto = { getPorId(id).getFoto().getId() };
            repositorio.deleteById(id);
            serviciosArchivo.borrar(idFoto);
        }
    }

    @Override
    public void modificar(Integer id, String nombre, String apellido, String usuario, long carnetIdentidad,
            MultipartFile foto) {
        boolean guardar = false;
        Usuario pivote = getPorId(id);
        if (pivote.getNombre().equals(nombre) == false) {
            pivote.setNombre(nombre);
            if (!guardar) {
                guardar = true;
            }
        }
        if (pivote.getApellido().equals(apellido) == false) {
            pivote.setApellido(apellido);
            if (!guardar) {
                guardar = true;
            }
        }
        if (pivote.getUsuario().equals(usuario) == false) {
            pivote.setUsuario(usuario);
            if (!guardar) {
                guardar = true;
            }
        }
        if (pivote.getCarnetIdentidad() != carnetIdentidad) {
            pivote.setCarnetIdentidad(carnetIdentidad);
            if (!guardar) {
                guardar = true;
            }
        }
        if (foto != null) {
            pivote.setFoto(serviciosArchivo.modificar(pivote.getFoto().getId(), foto));
            if (!guardar) {
                guardar = true;
            }
        }
        if (guardar) {
            repositorio.save(pivote);
        }
    }

    @Override
    public Usuario getPorId(Integer id) {
        return repositorio.findById(id).get();
    }

    @Override
    public UsuarioResp getPorIdR(Integer id) {
        return repositorio.findById(id).get().convertir();
    }

}
