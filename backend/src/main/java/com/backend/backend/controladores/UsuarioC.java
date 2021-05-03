package com.backend.backend.controladores;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.auxiliares.constantes.RolEnum;
import com.backend.backend.auxiliares.respuestas.ModUsuario;
import com.backend.backend.auxiliares.solicitudes.AdmRol;
import com.backend.backend.auxiliares.solicitudes.Modificar;
import com.backend.backend.auxiliares.solicitudes.NuevoUsuario;
import com.backend.backend.repositorios.entidades.Rol;
import com.backend.backend.repositorios.entidades.Usuario;
import com.backend.backend.servicios.UsuarioS;

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
@RequestMapping("/usuario")
public class UsuarioC {

    @Autowired
    private UsuarioS servicios;

    @GetMapping
    public ResponseEntity<List<ModUsuario>> listar() {
        return ResponseEntity.ok(convertirUsuario(servicios.listar()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ModUsuario> getUsuario(@PathVariable(required = true) Integer id) {
        return ResponseEntity.ok(servicios.getPorId(id).convertir());
    }

    @PutMapping
    public ResponseEntity<List<ModUsuario>> nuevo(@RequestBody(required = true) NuevoUsuario usuario) {
        servicios.salvar(usuario.getNombre(), usuario.getApellido(), usuario.getUsuario(),
                usuario.getCarnetIdentidad());
        return ResponseEntity.ok(convertirUsuario(servicios.listar()));
    }

    @DeleteMapping
    public ResponseEntity<List<ModUsuario>> eliminar(@RequestBody(required = true) Integer[] ids) {
        servicios.eliminar(ids);
        return ResponseEntity.ok(convertirUsuario(servicios.listar()));
    }

    @PostMapping
    public ResponseEntity<List<ModUsuario>> modificar(@RequestBody(required = true) Modificar solicitud) {
        servicios.modificar(solicitud.getId(), solicitud.getParametro(), solicitud.getNuevoValor());
        return ResponseEntity.ok(convertirUsuario(servicios.listar()));
    }

    @GetMapping("rol/{id}")
    public ResponseEntity<List<RolEnum>> listarRoles(@PathVariable(required = true) Integer id) {
        return ResponseEntity.ok(convertirRoles(servicios.getLisRolesPorId(id)));
    }

    @PostMapping("/rol")
    public ResponseEntity<List<RolEnum>> agregarRol(@RequestBody(required = true) AdmRol solicitud) {
        servicios.adjuntarRol(solicitud.getId(), solicitud.getRol());
        return ResponseEntity.ok(convertirRoles(servicios.getLisRolesPorId(solicitud.getId())));
    }

    @DeleteMapping("rol")
    public ResponseEntity<List<RolEnum>> removerRol(@RequestBody(required = true) AdmRol solicitud) {
        servicios.removerRol(solicitud.getId(), solicitud.getRol());
        return ResponseEntity.ok(convertirRoles(servicios.getLisRolesPorId(solicitud.getId())));
    }

    private List<ModUsuario> convertirUsuario(List<Usuario> lista) {
        List<ModUsuario> salida = new LinkedList<>();
        lista.forEach(us -> salida.add(us.convertir()));
        return salida;
    }

    private List<RolEnum> convertirRoles(List<Rol> roles) {
        List<RolEnum> salida = new LinkedList<>();
        roles.forEach(rol -> {
            salida.add(rol.getRol());
        });
        return salida;
    }
}