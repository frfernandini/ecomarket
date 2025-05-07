package com.fernandini.msvc.productos.services;

import com.fernandini.msvc.productos.models.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> findAll();
    Producto findById(Long id);
    Producto save(Producto producto);
    void deleteById(Producto producto);
    Producto update(Producto producto);
}
