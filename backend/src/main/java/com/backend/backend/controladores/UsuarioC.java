package com.backend.backend.controladores;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.auxiliares.constantes.RolConst;
import com.backend.backend.auxiliares.respuestas.DetalleUsuario;
import com.backend.backend.auxiliares.solicitudes.AdmiRol;
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
    public ResponseEntity<List<DetalleUsuario>> listar() {
        return ResponseEntity.ok(convertirUsuario(servicios.listar()));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalleUsuario> getUsuario(@PathVariable(required = true) Integer id) {
        return ResponseEntity.ok(servicios.getPorId(id).convertir());
    }

    @PutMapping
    public ResponseEntity<List<DetalleUsuario>> nuevo(@RequestBody(required = true) NuevoUsuario usuario) {
        servicios.salvar(usuario.getNombre(), usuario.getApellido(), usuario.getUsuario(),
                usuario.getCarnetIdentidad());
        return ResponseEntity.ok(convertirUsuario(servicios.listar()));
    }

    @DeleteMapping
    public ResponseEntity<List<DetalleUsuario>> eliminar(@RequestBody(required = true) Integer[] ids) {
        servicios.eliminar(ids);
        return ResponseEntity.ok(convertirUsuario(servicios.listar()));
    }

    @PostMapping
    public ResponseEntity<List<DetalleUsuario>> modificar(@RequestBody(required = true) Modificar solicitud) {
        servicios.modificar(solicitud.getId(), solicitud.getParametro(), solicitud.getNuevoValor());
        return ResponseEntity.ok(convertirUsuario(servicios.listar()));
    }

    @GetMapping("rol/{id}")
    public ResponseEntity<List<RolConst>> listarRoles(@PathVariable(required = true) Integer id) {
        return ResponseEntity.ok(convertirRoles(servicios.getLisRolesPorId(id)));
    }

    @PostMapping("/rol")
    public ResponseEntity<List<RolConst>> agregarRol(@RequestBody(required = true) AdmiRol solicitud) {
        servicios.adjuntarRol(solicitud.getId(), solicitud.getRol());
        return ResponseEntity.ok(convertirRoles(servicios.getLisRolesPorId(solicitud.getId())));
    }

    @DeleteMapping("rol")
    public ResponseEntity<List<RolConst>> removerRol(@RequestBody(required = true) AdmiRol solicitud) {
        servicios.removerRol(solicitud.getId(), solicitud.getRol());
        return ResponseEntity.ok(convertirRoles(servicios.getLisRolesPorId(solicitud.getId())));
    }

    private List<DetalleUsuario> convertirUsuario(List<Usuario> lista) {
        List<DetalleUsuario> salida = new LinkedList<>();
        lista.forEach(us -> salida.add(us.convertir()));
        return salida;
    }

    private List<RolConst> convertirRoles(List<Rol> roles) {
        List<RolConst> salida = new LinkedList<>();
        roles.forEach(rol -> {
            salida.add(rol.getRol());
        });
        return salida;
    }
}