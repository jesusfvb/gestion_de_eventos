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
    public List<Ponencia> listar() {
        return repositorio.findAll();
    }

    @Override
    public List<Ponencia> listarPorIdUsuario(Integer id) {
        return repositorio.getAllPorIdAutor(id);
    }

    @Override
    public List<Ponencia> listarPorIdRevisor(Integer id) {
        return repositorio.getAllPorIdRevisor(id);
    }

    @Override
    public List<Ponencia> listarPorIdSalaDePonencia(Integer id) {
        return repositorioSala.findById(id).get().getPonencias();
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

    @Override
    public void ponerEnRevision(Integer id, Integer idMiembroComision) {
        Ponencia ponencia = getPorId(id);
        ponencia.setRevisor(servicioUsuario.getPorId(idMiembroComision));
        repositorio.save(ponencia);
    }

    @Override
    public void aprobar(Integer idPonencia, Integer idSalaDePonencia) {

        Ponencia ponencia = getPorId(idPonencia);
        ponencia.setRevisor(null);
        repositorio.save(ponencia);
        SalaDePonencia sala = repositorioSala.findById(idSalaDePonencia).get();
        sala.getPonencias().add(ponencia);
        repositorioSala.save(sala);
    }

    @Override
    public Integer votarPorPonencia(Integer id) {
        Ponencia ponencia = getPorId(id);
        ponencia.setCantVotos(ponencia.getCantVotos() + 1);
        repositorio.save(ponencia);
        return ponencia.getCantVotos();
    }

    private Ponencia getPorId(Integer id) {
        return repositorio.findById(id).get();
    }

}
