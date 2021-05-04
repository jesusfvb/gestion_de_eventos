package com.backend.backend.controladores;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.auxiliares.respuestas.ModEvento;
import com.backend.backend.auxiliares.solicitudes.Modificar;
import com.backend.backend.auxiliares.solicitudes.NuevoEvento;
import com.backend.backend.auxiliares.solicitudes.SolicitarEvento;
import com.backend.backend.repositorios.entidades.Evento;
import com.backend.backend.servicios.EventoS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/evento")
public class EventoC {

    @Autowired
    private EventoS servicios;

    @GetMapping
    public ResponseEntity<List<ModEvento>> listar() {
        return ResponseEntity.ok(convertir(servicios.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModEvento> getPorId(@PathVariable(required = true) Integer id) {
        return ResponseEntity.ok(servicios.getPorId(id).convertir());
    }

    @PutMapping
    public ResponseEntity<List<ModEvento>> salvar(@RequestBody(required = true) NuevoEvento solicitud) {
        servicios.salvar(solicitud.getNombre(), solicitud.getArea(), solicitud.getClasificacion(),
                solicitud.getEdicion(), solicitud.getInicio(), solicitud.getFin());
        return ResponseEntity.ok(convertir(servicios.listar()));
    }

    @PostMapping
    public ResponseEntity<List<ModEvento>> modificar(@RequestBody(required = true) Modificar solicitud) {
        servicios.modificar(solicitud.getId(), solicitud.getParametro(), solicitud.getNuevoValor());
        return ResponseEntity.ok(convertir(servicios.listar()));
    }

    @DeleteMapping
    public ResponseEntity<List<ModEvento>> eliminar(@RequestBody(required = true) Integer[] ids) {
        servicios.eliminar(ids);
        return ResponseEntity.ok(convertir(servicios.listar()));
    }

    @PutMapping("/solicitar")
    public ResponseEntity<List<ModEvento>> solicitar(@RequestBody(required = true) SolicitarEvento solicitud) {
        servicios.solicitar(solicitud.getNombre(), solicitud.getArea(), solicitud.getClasificacion());
        return ResponseEntity.ok(convertir(servicios.listar()));
    }

    @PostMapping("/aprobar/{id}")
    public ResponseEntity<List<ModEvento>> aceptarSolicitud(@PathVariable(required = true) Integer id) {
        
        return ResponseEntity.ok(convertir(servicios.listar()));
    }

    private List<ModEvento> convertir(List<Evento> lista) {
        List<ModEvento> salida = new LinkedList<>();
        lista.forEach(evento -> salida.add(evento.convertir()));
        return salida;
    }
}
