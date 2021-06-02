package com.backend.backend.controladores;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.auxiliares.respuestas.ModComision;
import com.backend.backend.auxiliares.respuestas.UsuarioResp;
import com.backend.backend.auxiliares.solicitudes.AdmMiembro;
import com.backend.backend.auxiliares.solicitudes.Modificar;
import com.backend.backend.auxiliares.solicitudes.NuevaComision;
import com.backend.backend.repositorios.entidades.Comision;
import com.backend.backend.repositorios.entidades.Usuario;
import com.backend.backend.servicios.ComisionS;

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
@RequestMapping("/comision")
public class ComisionC {

    @Autowired
    private ComisionS servicios;

    @GetMapping
    public ResponseEntity<List<ModComision>> listar() {
        return respuesta();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModComision> getPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servicios.getPorId(id).convertir());
    }

    @PutMapping
    public ResponseEntity<List<ModComision>> salvar(@RequestBody(required = true) NuevaComision solicitud) {

        servicios.salvar(solicitud.getIdEvento(), solicitud.getNombre(), solicitud.getLineaTematica(),
                solicitud.getIdsComiteOrganizador());
        return respuesta();
    }

    @PostMapping
    public ResponseEntity<List<ModComision>> modificar(@RequestBody(required = true) Modificar solicitud) {
        servicios.modificar(solicitud.getId(), solicitud.getParametro(), solicitud.getNuevoValor());
        return respuesta();
    }

    @DeleteMapping
    public ResponseEntity<List<ModComision>> borrar(@RequestBody(required = true) Integer[] ids) {
        servicios.borrar(ids);
        return respuesta();
    }

    @GetMapping("/miembros/{id}")
    public ResponseEntity<List<UsuarioResp>> getMiembros(@PathVariable(required = true) Integer id) {
        return respuestaMiembros(id);
    }

    @PutMapping("/miembros")
    public ResponseEntity<List<UsuarioResp>> agregarMiembro(@RequestBody(required = true) AdmMiembro solicitud) {
        servicios.agregarMiembro(solicitud.getId(), solicitud.getIdMiembro());
        return respuestaMiembros(solicitud.getId());
    }

    @DeleteMapping("/miembros")
    public ResponseEntity<List<UsuarioResp>> removerMiembro(@RequestBody(required = true) AdmMiembro solicitud) {
        servicios.eliminarMiembro(solicitud.getId(), solicitud.getIdMiembro());
        return respuestaMiembros(solicitud.getId());
    }

    private ResponseEntity<List<ModComision>> respuesta() {
        List<ModComision> salida = new LinkedList<>();
        for (Comision comision : servicios.listar()) {
            salida.add(comision.convertir());
        }
        return ResponseEntity.ok(salida);
    }

    private ResponseEntity<List<UsuarioResp>> respuestaMiembros(Integer id) {
        List<UsuarioResp> salida = new LinkedList<>();
        for (Usuario usuario : servicios.getMiembros(id)) {
            salida.add(usuario.convertir());
        }
        return ResponseEntity.ok(salida);
    }
}
