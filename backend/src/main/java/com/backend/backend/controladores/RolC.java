package com.backend.backend.controladores;

import java.util.List;

import com.backend.backend.auxiliares.constantes.RolEnum;
import com.backend.backend.servicios.RolS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/rol")
public class RolC {

    @Autowired
    private RolS servicios;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<RolEnum>> listarRoles(@PathVariable(required = true, name = "idUsuario") Integer id) {
        return ResponseEntity.ok(servicios.listarLosDeUsuario(id));
    }

    @PutMapping
    public ResponseEntity<Boolean> gestionRol(@RequestParam Integer id, @RequestParam String[] roles) {
        return ResponseEntity.ok(servicios.gestionRol(id, roles));
    }
}
