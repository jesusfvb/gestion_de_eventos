package com.backend.backend.controller;

import com.backend.backend.controller.response.ConvocatoriaResponse;
import com.backend.backend.repository.entity.MyRole;
import com.backend.backend.service.ConvocatoriaService;
import com.backend.backend.service.RoleService;
import com.backend.backend.service.UserService;

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

import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/convocatoria")
public class ConvocatoriaController {

    @Autowired
    private ConvocatoriaService convocatoriaService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<ConvocatoriaResponse>> list() {
        List<ConvocatoriaResponse> convocatoriaResponseList = new LinkedList<>();
        convocatoriaService.list().forEach(convocatoria -> {
            ConvocatoriaResponse convocatoriaResponse = convocatoria.transform();
            for (MyRole role : roleService.getByUserId(convocatoriaResponse.getConvocatoriaBoss().getId())) {
                convocatoriaResponse.getConvocatoriaBoss().getRoles().add(role.getRol().name());
            }
            convocatoriaResponseList.add(convocatoriaResponse);
        });
        return ResponseEntity.ok(convocatoriaResponseList);
    }


    @GetMapping("/boss")
    public ResponseEntity<List<ConvocatoriaResponse>> listByBossId(@RequestParam String username) {
        List<ConvocatoriaResponse> convocatoriaResponseList = new LinkedList<>();
        convocatoriaService.listByBossUsername(username).forEach(convocatoria -> {
            ConvocatoriaResponse convocatoriaResponse = convocatoria.transform();
            for (MyRole role : roleService.getByUserId(convocatoriaResponse.getConvocatoriaBoss().getId())) {
                convocatoriaResponse.getConvocatoriaBoss().getRoles().add(role.getRol().name());
            }
            convocatoriaResponseList.add(convocatoriaResponse);
        });
        return ResponseEntity.ok(convocatoriaResponseList);
    }

    @PostMapping
    public ResponseEntity<Boolean> save(@RequestParam String text) {
        convocatoriaService.save(text);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping
    private ResponseEntity<Boolean> delete(@RequestParam Integer[] ids) {
        convocatoriaService.delete(ids);
        return ResponseEntity.ok(true);
    }

    @PutMapping
    private ResponseEntity<Boolean> update(@RequestParam Integer id, @RequestParam String text) {
        convocatoriaService.update(id, text);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/boss")
    private ResponseEntity<Boolean> adjuntarBoos(@RequestParam Integer id, @RequestParam Integer idUser) {
        convocatoriaService.adjuntarBoss(id, userService.getById(idUser));
        return ResponseEntity.ok(true);
    }
}
