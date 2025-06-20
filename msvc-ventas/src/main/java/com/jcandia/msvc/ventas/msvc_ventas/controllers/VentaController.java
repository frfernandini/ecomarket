package com.jcandia.msvc.ventas.msvc_ventas.controllers;

import com.jcandia.msvc.ventas.msvc_ventas.dto.ListaVentasUsuarioDTO;
import com.jcandia.msvc.ventas.msvc_ventas.models.entities.Ventas;
import com.jcandia.msvc.ventas.msvc_ventas.services.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ventas")
@Validated
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Ventas>> findByAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ventaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ventas> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ventaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Ventas> save(@RequestBody Ventas venta) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ventaService.save(venta));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<ListaVentasUsuarioDTO> findByIdUsuario(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ventaService.findByIdUsuario(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        ventaService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ventas> update(@PathVariable Long id, @Valid @RequestBody Ventas ventas) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ventaService.update(id,ventas));
    }
}
