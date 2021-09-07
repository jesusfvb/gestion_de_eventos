package com.backend.backend.controller;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.controller.response.EventoResponse;
import com.backend.backend.repository.entity.Evento.State;
import com.backend.backend.service.EventoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/event")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    private ResponseEntity<List<EventoResponse>> getByIdConvocatoria(@RequestParam Integer id,
            @RequestParam State state) {
        List<EventoResponse> list = new LinkedList<>();
        eventoService.getByIdConvocatoriaAndState(id, state).forEach(evento -> list.add(evento.transform()));
        return ResponseEntity.ok(list);
    }

    @GetMapping("/all")
    private ResponseEntity<List<EventoResponse>> getByIdConvocatoria(@RequestParam Integer id) {
        List<EventoResponse> list = new LinkedList<>();
        eventoService.getByIdConvocatoria(id).forEach(evento -> list.add(evento.transform()));
        return ResponseEntity.ok(list);
    }

    @PostMapping
    private ResponseEntity<Boolean> save(@RequestParam Integer idConvocatoria, @RequestParam String name) {
        eventoService.save(idConvocatoria, name);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping
    private ResponseEntity<Boolean> delete(@RequestParam Integer[] ids) {
        eventoService.delete(ids);
        return ResponseEntity.ok(true);
    }

    @PutMapping
    private ResponseEntity<Boolean> update(@RequestParam Integer id, @RequestParam String name) {
        eventoService.update(id, name);
        return ResponseEntity.ok(true);
    }
}
