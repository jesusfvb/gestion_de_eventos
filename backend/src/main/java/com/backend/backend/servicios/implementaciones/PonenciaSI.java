package com.backend.backend.servicios.implementaciones;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.backend.backend.repositorios.ComentarioR;
import com.backend.backend.repositorios.PonenciaR;
import com.backend.backend.repositorios.SalaDePonenciaR;
import com.backend.backend.repositorios.entidades.Comentario;
import com.backend.backend.repositorios.entidades.Ponencia;
import com.backend.backend.repositorios.entidades.SalaDePonencia;
import com.backend.backend.repositorios.entidades.Usuario;
import com.backend.backend.servicios.EventoS;
import com.backend.backend.servicios.PonenciaS;
import com.backend.backend.servicios.UsuarioS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class PonenciaSI implements PonenciaS {

    @Autowired
    private PonenciaR repositorio;

    @Autowired
    private SalaDePonenciaR repositorioSala;

    @Autowired
    private ComentarioR repositorioComentario;

    @Autowired
    private EventoS servicioEvento;

    @Autowired
    private UsuarioS servicioUsuario;

    @Override
    public List<SalaDePonencia> listarSalaDePonencia(Integer id) {
        return servicioEvento.getPorId(id).getSalasDePonencia();
    }

    @Override
    public List<Ponencia> listar(Integer id) {
        return repositorio.findAll(Example.of(new Ponencia(servicioUsuario.getPorId(id), null, null, null)));
    }

    @Override
    public List<Comentario> listarComentario(Integer id) {
        return getPorId(id).getComentarios();
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

    @Override
    public void comentar(Integer id, Integer idUsuario, String comentario) {
        Ponencia ponencia = getPorId(id);
        ponencia.getComentarios()
                .add(repositorioComentario.save(new Comentario(servicioUsuario.getPorId(idUsuario), comentario)));
    }

    @Override
    public void registrar(Integer idAutor, String nombre, File archivo, Integer[] idsCoautores) {
        List<Usuario> coautores = new LinkedList<>();
        for (Integer id : idsCoautores) {
            coautores.add(servicioUsuario.getPorId(id));
        }
        repositorio.save(new Ponencia(servicioUsuario.getPorId(idAutor), nombre, archivo, coautores));
    }

    private Ponencia getPorId(Integer id) {
        return repositorio.findById(id).get();
    }

}
