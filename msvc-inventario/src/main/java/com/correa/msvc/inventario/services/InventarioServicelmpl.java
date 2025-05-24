package com.correa.msvc.inventario.services;

import com.correa.msvc.inventario.clients.ProductoClientRest;
import com.correa.msvc.inventario.clients.SucursalClientRest;
import com.correa.msvc.inventario.exceptions.InventarioException;
import com.correa.msvc.inventario.models.Producto;
import com.correa.msvc.inventario.models.Sucursal;
import com.correa.msvc.inventario.models.entities.Inventario;
import com.correa.msvc.inventario.repositories.InventarioRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventarioServicelmpl implements InventarioService{

    @Autowired
    private ProductoClientRest productoClientRest;

    @Autowired
    private SucursalClientRest sucursalClientRest;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public List<Inventario> findAll(){
        return this.inventarioRepository.findAll();}

    @Override
    public Inventario findById(Long id) {
        return this.inventarioRepository.findById(id).orElseThrow(
                ()-> new InventarioException("No se encontro el id "+id+" en la base de datos")
        );
    }

    @Override
    public Inventario save(Inventario inventario){
        try {
            Producto producto = this.productoClientRest.findById(inventario.getIdProducto());
            Sucursal sucursal = this.sucursalClientRest.findById(inventario.getIdSucursal());
            return this.inventarioRepository.save(inventario);
        }catch (FeignException ex){
            throw new InventarioException(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        inventarioRepository.deleteById(id);

    }


}
