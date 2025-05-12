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
@RequestMapping("/api/v1/usuarios")
@Validated
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    private ResponseEntity<List<Usuarios>> findByAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Usuarios> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioService.findById(id));
    }

    @GetMapping("/run/{runUsuario}")
    private ResponseEntity<Usuarios> findByrun(@PathVariable String run) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioService.findByRun(run));
    }

    @PostMapping
    private ResponseEntity<Usuarios> save(@Valid @RequestBody Usuarios usuarios) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.save(usuarios));
    }

    @PutMapping("/{id}")
    private ResponseEntity<Usuarios> update(@PathVariable Long id, @Valid @RequestBody Usuarios usuarios) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.update(id,usuarios));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
