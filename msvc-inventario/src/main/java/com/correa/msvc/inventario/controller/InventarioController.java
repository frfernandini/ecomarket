package com.correa.msvc.inventario.controller;

import com.correa.msvc.inventario.models.Inventario;
import com.correa.msvc.inventario.services.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("api/v1/inventario")
@Validated

public class InventarioController {


    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> findByAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventarioService.findAll());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventarioService.findById(id));
    }
}
