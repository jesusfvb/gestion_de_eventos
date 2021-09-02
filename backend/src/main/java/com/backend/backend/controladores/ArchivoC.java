package com.backend.backend.controladores;

import java.util.List;

import com.backend.backend.auxiliares.respuestas.ArchivoResp;
import com.backend.backend.repositorios.entidades.Archivo;
import com.backend.backend.servicios.ArchivoS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/archivo")
public class ArchivoC {

    @Autowired
    private ArchivoS serviceArchivo;

    @GetMapping
    public ResponseEntity<List<ArchivoResp>> listar() {
        return ResponseEntity.ok(serviceArchivo.listResp());
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getById(@PathVariable(required = true) Integer id) {
        Archivo archivo = serviceArchivo.getPorId(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archivo.getName() + "\"")
                .contentType(MediaType.valueOf(archivo.getContentType())).body(archivo.getData());
    }

    @GetMapping("/respuesta/{id}")
    public ResponseEntity<ArchivoResp> getByIdR(@PathVariable(required = true) Integer id) {
        Archivo archivo = serviceArchivo.getPorId(id);
        return ResponseEntity.ok(archivo.convertir());
    }

    @PostMapping
    public ResponseEntity<byte[]> salvar(@RequestParam(name = "file") MultipartFile file) {
        return getById(serviceArchivo.save(file).getId());
    }

}
