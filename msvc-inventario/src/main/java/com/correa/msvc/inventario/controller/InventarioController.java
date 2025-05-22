package com.correa.msvc.inventario.controller;

import com.correa.msvc.inventario.models.Inventario;
import com.correa.msvc.inventario.services.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<Inventario> save(@RequestBody @Valid Inventario inventario){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.inventarioService.save(inventario));
    }

    //@GetMapping ("/inventario/{id}")
    //public ResponseEntity<List<Inventario>>findbyIdInventario(@PathVariable Long id){
        //return ResponseEntity.status(HttpStatus.OK).body(this.inventarioService.findBy(id));
    }
}
