package com.backend.backend.controladores;

import java.util.List;

import com.backend.backend.auxiliares.respuestas.ConvocatoriaResp;
import com.backend.backend.servicios.ConvocatoriaS;

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

@RestController
@CrossOrigin("*")
@RequestMapping("/convocatoria")
public class ConvocatoriaC {

    @Autowired
    private ConvocatoriaS servicios;

    @GetMapping
    public ResponseEntity<List<ConvocatoriaResp>> listarR() {
        return ResponseEntity.ok(servicios.listarR());
    }

    @PostMapping
    public ResponseEntity<Boolean> nueva(@RequestParam String convocatoria) {
        servicios.salvar(convocatoria);
        return ResponseEntity.ok(true);
    }

    @PutMapping
    public ResponseEntity<Boolean> modificar(@RequestParam Integer id, @RequestParam String convocatoria) {
        servicios.modificar(id, convocatoria);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<Boolean> borrar(@PathVariable Integer[] ids) {
        servicios.eliminar(ids);
        return ResponseEntity.ok(true);
    }

}
