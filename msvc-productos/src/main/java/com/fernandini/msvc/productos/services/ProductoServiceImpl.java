package com.fernandini.msvc.productos.services;

import com.fernandini.msvc.productos.clients.ProveedorClientRest;
import com.fernandini.msvc.productos.exceptions.ProductoException;
import com.fernandini.msvc.productos.models.Producto;
import com.fernandini.msvc.productos.models.Proveedor;
import com.fernandini.msvc.productos.repositories.ProductoRepository;
import feign.FeignException;
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
        try {
            Proveedor proveedor = this.proveedorClientRest.findById(producto.getProveedorId());
            return this.productoRepository.save(producto);
        }catch (FeignException ex) {
            throw new ProductoException(ex.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Producto update(Long id,Producto producto) {
        try {
            Proveedor proveedor = this.proveedorClientRest.findById(producto.getProveedorId());
        }catch (FeignException ex) {
            throw new ProductoException("el proveedor con id: "+producto.getProveedorId()+" no esta registrado");
        }
        return productoRepository.findById(id).map(p -> {
            p.setNombreProducto(producto.getNombreProducto());
            p.setDescProducto(producto.getDescProducto());
            p.setCategoriaProducto(producto.getCategoriaProducto());
            p.setPrecioProducto(producto.getPrecioProducto());
            return productoRepository.save(p);
        } ).orElseThrow(
                () -> new ProductoException("Paciente con id"+id+"no encontrado")
        );
    }
}
