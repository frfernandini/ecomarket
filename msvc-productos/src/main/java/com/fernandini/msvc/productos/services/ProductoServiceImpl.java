package com.fernandini.msvc.productos.services;

import com.fernandini.msvc.productos.clients.ProveedorClientRest;
import com.fernandini.msvc.productos.exceptions.ProductoException;
import com.fernandini.msvc.productos.models.Producto;
import com.fernandini.msvc.productos.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorClientRest proveedorClientRest;

    @Override
    public List<Producto> findAll() {
        return this.productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {

        return this.productoRepository.findById(id).orElseThrow(
                () -> new ProductoException("el paciente con id: "+id+" no esta registrado")
        );
    }

    @Override
    public Producto save(Producto producto) {

    }

    @Override
    public void deleteById(Producto producto) {

    }

    @Override
    public Producto update(Producto producto) {
        return null;
    }
}
