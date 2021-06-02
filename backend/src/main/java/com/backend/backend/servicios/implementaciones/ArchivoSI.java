package com.backend.backend.servicios.implementaciones;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.auxiliares.respuestas.ArchivoResp;
import com.backend.backend.repositorios.ArchivoR;
import com.backend.backend.repositorios.entidades.Archivo;
import com.backend.backend.servicios.ArchivoS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArchivoSI implements ArchivoS {

    @Autowired
    private ArchivoR repositorio;

    @Override
    public List<ArchivoResp> listResp() {
        List<ArchivoResp> salida = new LinkedList<>();
        repositorio.findAll().forEach(archivo -> {
            salida.add(archivo.convertir());
        });
        return salida;
    }

    @Override
    public Archivo save(MultipartFile archivo) {
        Archivo salida = null;
        try {
            salida = repositorio.save(
                    new Archivo(archivo.getName(), archivo.getContentType(), archivo.getSize(), archivo.getBytes()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return salida;
    }

    @Override
    public Archivo modificar(Integer id, MultipartFile archivo) {
        Archivo pivote = getPorId(id);
        pivote.setName(archivo.getName());
        pivote.setContentType(archivo.getContentType());
        try {
            pivote.setData(archivo.getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        pivote.setSize(archivo.getSize());
        return repositorio.save(pivote);
    }

    @Override
    public Archivo getPorId(Integer id) {
        return repositorio.findById(id).get();
    }

    @Override
    public void borrar(Integer[] ids) {
        for (Integer id : ids) {
            repositorio.deleteById(id);
        }
    }

}
