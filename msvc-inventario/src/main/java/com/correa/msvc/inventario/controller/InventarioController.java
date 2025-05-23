package com.correa.msvc.inventario.controller;

import com.correa.msvc.inventario.models.Inventario;
import com.correa.msvc.inventario.services.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/inventario")
@Validated

public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>>findAll(){
        List<Inventario>inventarios = inventarioService.findAll();
        if(inventarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inventarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Inventario> inventarioOpt= inventarioService.findById(id);
        return  inventarioOpt.map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Inventario inventario,
                                    BindingResult result,
                                    @PathVariable Long id){
        if(result.hasErrors()){
            return validar(result);
        }
        Optional<Inventario>inventarioOpt=InventarioService.findById(id);
        if(inventarioOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Inventario inventarioDb= inventarioOpt.get();
        inventarioDb.setCantidadInventario(inventario.getCantidadInventario());
        inventarioDb.setFecha_ingreso_producto(inventario.getFecha_ingreso_producto());

        return ResponseEntity.ok(InventarioService.save(inventarioDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Inventario> inventarioOpt= inventarioService.findById(id);
        if(inventarioOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        inventarioService.delete(id);
        return ResponseEntity.noContent().build();
    }





}
