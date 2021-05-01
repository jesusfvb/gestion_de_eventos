package com.backend.backend.servicios;

import java.util.List;

import com.backend.backend.repositorios.entidades.Usuario;

import org.springframework.stereotype.Service;

@Service
public interface UsuarioS {
    
    public List<Usuario>  listar();

    public void salvar(String nombre,String apellido,String usuario,Integer carnetIdentidad);

    public void eliminar(Integer[] ids);

}
