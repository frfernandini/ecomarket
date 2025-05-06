package com.ecomarket.msvc_proveedores.controllers;

import com.ecomarket.msvc_proveedores.models.Proveedor;
import com.ecomarket.msvc_proveedores.services.ProveedorService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/proveedores")
@Validated
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(proveedorService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(proveedorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Proveedor> save(@Valid @RequestBody Proveedor proveedor) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(proveedorService.save(proveedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> update(@PathVariable Long id, @Valid @RequestBody Proveedor proveedor) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(proveedorService.update(id, proveedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        proveedorService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
