package com.cjohao.msvc.usuarios.msvc_usuarios.controllers;


import com.cjohao.msvc.usuarios.msvc_usuarios.models.Usuarios;
import com.cjohao.msvc.usuarios.msvc_usuarios.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
@Validated
public class UsuarioController {


    @Autowired
    public UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuarios>> findByAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioService.findById(id));
    }

    @GetMapping("/run/{runUsuario}")
    public ResponseEntity<Usuarios> findByRun(@PathVariable String runUsuario) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioService.findByRun(runUsuario));
    }

    @PostMapping
    public ResponseEntity<Usuarios> save(@Valid @RequestBody Usuarios usuarios) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.save(usuarios));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuarios> update(@PathVariable Long id, @Valid @RequestBody Usuarios usuarios) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.update(id,usuarios));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
