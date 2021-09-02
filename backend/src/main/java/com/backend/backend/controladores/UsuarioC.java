package com.backend.backend.controladores;

import java.util.List;

import com.backend.backend.auxiliares.respuestas.UsuarioResp;
import com.backend.backend.servicios.UsuarioS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioC {

    @Autowired
    private UsuarioS servicios;

    @GetMapping
    public ResponseEntity<List<UsuarioResp>> listar() {
        return ResponseEntity.ok(servicios.listarR());
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioResp> getUsuario(@PathVariable(required = true) Integer id) {
        return ResponseEntity.ok(servicios.getPorIdR(id));
    }

    @PostMapping
    public ResponseEntity<List<UsuarioResp>> nuevo(@RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String usuario, @RequestParam long carnetIdentidad,
            @RequestParam(required = false) MultipartFile foto) throws Exception {
        servicios.salvar(nombre, apellido, usuario, carnetIdentidad, foto);
        return ResponseEntity.ok(servicios.listarR());
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<List<UsuarioResp>> eliminar(@PathVariable(required = true) Integer[] ids) {
        servicios.eliminar(ids);
        return ResponseEntity.ok(servicios.listarR());
    }

    @PutMapping
    public ResponseEntity<List<UsuarioResp>> modificar(@RequestParam Integer id, @RequestParam String nombre,
            @RequestParam String apellido, @RequestParam String usuario, @RequestParam long carnetIdentidad,
            @RequestParam(required = false) MultipartFile foto) throws Exception {
        servicios.modificar(id, nombre, apellido, usuario, carnetIdentidad, foto);
        return ResponseEntity.ok(servicios.listarR());
    }

}