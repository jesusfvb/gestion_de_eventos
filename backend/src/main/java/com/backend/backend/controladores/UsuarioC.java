package com.backend.backend.controladores;

import java.util.LinkedList;
import java.util.List;

import com.backend.backend.auxiliares.constantes.RolConst;
import com.backend.backend.auxiliares.respuestas.ListarUsuario;
import com.backend.backend.auxiliares.solicitudes.NuevoUsuario;
import com.backend.backend.repositorios.entidades.Usuario;
import com.backend.backend.servicios.UsuarioS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<ListarUsuario>> listar() {
        return ResponseEntity.ok(ListUsuario_ListListarUsuario(servicios.listar()));
    }

    @PutMapping
    public ResponseEntity<List<ListarUsuario>> nuevo(@RequestBody NuevoUsuario usuario) {
        servicios.salvar(usuario.getNombre(), usuario.getApellido(), usuario.getUsuario(),
                usuario.getCarnetIdentidad());
        return ResponseEntity.ok(ListUsuario_ListListarUsuario(servicios.listar()));
    }

    @DeleteMapping
    public ResponseEntity<List<ListarUsuario>> eliminar(@RequestBody Integer[] ids) {
        servicios.eliminar(ids);
        return ResponseEntity.ok(ListUsuario_ListListarUsuario(servicios.listar()));
    }

    private List<ListarUsuario> ListUsuario_ListListarUsuario(List<Usuario> lista) {
        List<ListarUsuario> salida = new LinkedList<>();
        List<RolConst> roles = new LinkedList<>();
        lista.forEach(usuario -> {
            roles.clear();
            usuario.getRoles().forEach(rol -> {
                roles.add(rol.getRol());
            });
            salida.add(new ListarUsuario(usuario.getId(), usuario.getNombre(), usuario.getApellido(),
                    usuario.getUsuario(), usuario.getCarnetIdentidad(), roles));
        });
        return salida;
    }
}
