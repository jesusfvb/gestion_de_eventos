package com.backend.backend.controladores;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.auxiliares.respuestas.ModComentario;
import com.backend.backend.auxiliares.respuestas.ModPonencia;
import com.backend.backend.auxiliares.respuestas.ModSalaDePonencia;
import com.backend.backend.auxiliares.solicitudes.AdmSalaDePonencia;
import com.backend.backend.auxiliares.solicitudes.NuevaPonencia;
import com.backend.backend.auxiliares.solicitudes.NuevaSalaDePonencia;
import com.backend.backend.auxiliares.solicitudes.NuevoComentario;
import com.backend.backend.repositorios.entidades.Comentario;
import com.backend.backend.repositorios.entidades.Ponencia;
import com.backend.backend.repositorios.entidades.SalaDePonencia;
import com.backend.backend.servicios.PonenciaS;

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
@RequestMapping("/ponencia")
public class PonenciaC {

    @Autowired
    private PonenciaS servicios;

    @GetMapping("/{id}")
    public ResponseEntity<List<ModPonencia>> listar(@PathVariable(required = true) Integer id) {
        return respuestaPonencia(id);
    }

    @PutMapping
    public ResponseEntity<List<ModPonencia>> registrar(@RequestBody(required = true) NuevaPonencia solicitud) {
        servicios.registrar(solicitud.getIdAutor(), solicitud.getNombre(), solicitud.getArchivo(),
                solicitud.getIdsCoautores());
        return respuestaPonencia(solicitud.getIdAutor());
    }

    @PutMapping("/comentar")
    public ResponseEntity<List<ModComentario>> comentar(@RequestBody(required = true) NuevoComentario solicitud) {
        servicios.comentar(solicitud.getId(), solicitud.getIdUsuario(), solicitud.getComentario());
        return respuestaComentarios(solicitud.getId());
    }

    @GetMapping("/sala/{id}")
    public ResponseEntity<List<ModSalaDePonencia>> listarSalasDePonencia(@PathVariable(required = true) Integer id) {
        return respuestaSalasDePonencia(id);
    }

    @PutMapping("/sala")
    public ResponseEntity<List<ModSalaDePonencia>> nuevaSalaDePonencia(
            @RequestBody(required = true) NuevaSalaDePonencia solicitud) {
        servicios.nuevaSalaDePonencia(solicitud.getIdEvento(), solicitud.getNombre());
        return respuestaSalasDePonencia(solicitud.getIdEvento());
    }

    @DeleteMapping("/sala")
    public ResponseEntity<List<ModSalaDePonencia>> eliminarSalaDePonencia(
            @RequestBody(required = true) AdmSalaDePonencia solicitud) {
        servicios.eliminarSalaDePonencia(solicitud.getIdEvento(), solicitud.getId());
        return respuestaSalasDePonencia(solicitud.getIdEvento());
    }

    private ResponseEntity<List<ModPonencia>> respuestaPonencia(Integer id) {
        List<ModPonencia> salida = new LinkedList<>();
        for (Ponencia ponencia : servicios.listar(id)) {
            salida.add(ponencia.convertir());
        }
        return ResponseEntity.ok(salida);
    }

    private ResponseEntity<List<ModSalaDePonencia>> respuestaSalasDePonencia(Integer id) {
        List<ModSalaDePonencia> salida = new LinkedList<>();
        for (SalaDePonencia sala : servicios.listarSalaDePonencia(id)) {
            salida.add(sala.convertir());
        }
        return ResponseEntity.ok(salida);
    }

    private ResponseEntity<List<ModComentario>> respuestaComentarios(Integer id) {
        List<ModComentario> salida = new LinkedList<>();
        for (Comentario comentario : servicios.listarComentario(id)) {
            salida.add(comentario.convertir());
        }
        return ResponseEntity.ok(salida);
    }
}
