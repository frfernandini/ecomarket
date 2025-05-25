package com.correa.msvc.inventario.controller;

import com.correa.msvc.inventario.models.entities.Inventario;
import com.correa.msvc.inventario.services.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventarios")
@Validated
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
    public ResponseEntity<Inventario>save(@Valid @RequestBody Inventario inventario){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.save(inventario));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        inventarioService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/descontar-stock/{idProducto}")
    public ResponseEntity<Inventario> descontarStock(@PathVariable Long idProducto, @Valid @RequestBody Integer cantidadVentas) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventarioService.descontarCantidad(idProducto, cantidadVentas));
    }

}
