package com.backend.backend.servicios;

import java.util.List;

import com.backend.backend.auxiliares.respuestas.UsuarioResp;
import com.backend.backend.auxiliares.solicitudes.UsuarioSoli;
import com.backend.backend.repositorios.entidades.Usuario;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UsuarioS {

    public List<UsuarioResp> listarR();

    public void salvar(UsuarioSoli usuario, MultipartFile foto);

    public boolean salvar(Usuario usuario);

    public void eliminar(Integer[] ids);

    public void modificar(Integer id, UsuarioSoli usuario, MultipartFile foto);

    public Usuario getPorId(Integer id);

    public UsuarioResp getPorIdR(Integer id);

}
