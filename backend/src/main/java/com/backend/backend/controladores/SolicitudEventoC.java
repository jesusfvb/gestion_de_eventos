package com.backend.backend.controladores;

import java.util.List;

import com.backend.backend.auxiliares.respuestas.SolicitudEventoResp;
import com.backend.backend.servicios.SolicitudEventoS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/evento/solicitud")
public class SolicitudEventoC {

    @Autowired
    private SolicitudEventoS servicios;

    @GetMapping
    private ResponseEntity<List<SolicitudEventoResp>> listar() {
        return ResponseEntity.ok(servicios.listar());
    }

    @PostMapping
    private ResponseEntity<Boolean> salvar(@RequestParam String nombreEvento, @RequestParam String clasificacion,
            @RequestParam String area) {
        servicios.salvar(nombreEvento, clasificacion, area);
        return ResponseEntity.ok(true);
    }

}
