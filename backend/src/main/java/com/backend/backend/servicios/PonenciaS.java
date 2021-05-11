package com.backend.backend.servicios;

import java.io.File;
import java.util.List;

import com.backend.backend.repositorios.entidades.Comentario;
import com.backend.backend.repositorios.entidades.Ponencia;
import com.backend.backend.repositorios.entidades.SalaDePonencia;

import org.springframework.stereotype.Service;

@Service
public interface PonenciaS {

    public List<SalaDePonencia> listarSalaDePonencia(Integer id);

    public List<Ponencia> listar();

    public List<Ponencia> listarPorIdUsuario(Integer id);

    public List<Ponencia> listarPorIdRevisor(Integer id);

    public List<Ponencia> listarPorIdSalaDePonencia(Integer id);

    public List<Comentario> listarComentario(Integer id);

    public void nuevaSalaDePonencia(Integer id, String nombre);

    public void eliminarSalaDePonencia(Integer id, Integer idEvento);

    public void comentar(Integer id, Integer idUsuario, String comentario);

    public void registrar(Integer idAutor, String nombre, File archivo, Integer[] idsCoautores);

    public void ponerEnRevision(Integer id, Integer idMiembroComision);

    public void aprobar(Integer idPonencia, Integer idSalaDePonencia);

    public Integer votarPorPonencia(Integer id);
}
