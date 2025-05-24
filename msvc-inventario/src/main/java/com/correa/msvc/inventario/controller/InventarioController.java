package com.correa.msvc.inventario.controller;

import com.correa.msvc.inventario.models.Inventario;
import com.correa.msvc.inventario.services.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventarioService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventarioService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Inventario>

}
