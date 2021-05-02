package com.backend.backend.controladores;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.auxiliares.respuestas.DetalleUsuario;
import com.backend.backend.auxiliares.solicitudes.NuevoUsuario;
import com.backend.backend.repositorios.entidades.Usuario;
import com.backend.backend.servicios.UsuarioS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioC {

    @Autowired
    private UsuarioS servicios;

    @GetMapping
    public ResponseEntity<List<DetalleUsuario>> listar() {
        return ResponseEntity.ok(convertir(servicios.listar()));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalleUsuario> getUsuario(@PathVariable(required = true) Integer id) {
        return ResponseEntity.ok(servicios.getPorId(id).convertir());
    }

    @PutMapping
    public ResponseEntity<List<DetalleUsuario>> nuevo(@RequestBody NuevoUsuario usuario) {
        servicios.salvar(usuario.getNombre(), usuario.getApellido(), usuario.getUsuario(),
                usuario.getCarnetIdentidad());
       return ResponseEntity.ok(convertir(servicios.listar()));
    }

    @DeleteMapping
    public ResponseEntity<List<DetalleUsuario>> eliminar(@RequestBody Integer[] ids) {
        servicios.eliminar(ids);
       return ResponseEntity.ok(convertir(servicios.listar()));
    }

    private List<DetalleUsuario> convertir(List<Usuario> lista) {
        List<DetalleUsuario> salida = new LinkedList<>();
        lista.forEach(us -> salida.add(us.convertir()));
        return salida;
    }
}