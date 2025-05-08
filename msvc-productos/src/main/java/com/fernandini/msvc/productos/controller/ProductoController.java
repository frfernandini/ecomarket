package com.fernandini.msvc.productos.controller;

import com.fernandini.msvc.productos.models.Producto;
import com.fernandini.msvc.productos.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/productos")
@Validated
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> findAll(){
        List<Producto> productos = this.productoService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable Long id){
        Producto producto = this.productoService.findById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(producto);

    }
}
