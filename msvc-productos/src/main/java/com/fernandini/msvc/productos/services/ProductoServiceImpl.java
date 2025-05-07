package com.fernandini.msvc.productos.services;

import com.fernandini.msvc.productos.models.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{
    @Override
    public List<Producto> findAll() {
        return List.of();
    }

    @Override
    public Producto findById(Long id) {
        return null;
    }

    @Override
    public Producto save(Producto producto) {
        return null;
    }

    @Override
    public void deleteById(Producto producto) {

    }

    @Override
    public Producto update(Producto producto) {
        return null;
    }
}
