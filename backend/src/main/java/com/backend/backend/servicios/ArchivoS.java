package com.backend.backend.servicios;

import java.util.List;

import com.backend.backend.auxiliares.respuestas.ArchivoResp;
import com.backend.backend.repositorios.entidades.Archivo;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ArchivoS {

    public List<ArchivoResp> listResp();

    public Archivo save(MultipartFile archivo);

    public Archivo modificar(Integer id, MultipartFile archivo);

    public Archivo getPorId(Integer id);

    public void borrar(Integer[] ids);
}
