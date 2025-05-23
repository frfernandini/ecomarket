package com.correa.msvc.inventario.controllers;


import com.correa.msvc.inventario.models.Sucursal;
import com.correa.msvc.inventario.services.SucursalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sucursales")
@Validated
public class SurcursalController {
    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<Sucursal>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sucursalService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sucursalService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Sucursal> save(@Valid @RequestBody Sucursal sucursal) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sucursalService.save(sucursal));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> update(@PathVariable Long id, @Valid @RequestBody Sucursal sucursal) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sucursalService.update(sucursal, id));
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sucursalService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
