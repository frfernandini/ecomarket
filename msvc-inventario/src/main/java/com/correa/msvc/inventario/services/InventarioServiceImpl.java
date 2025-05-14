package com.correa.msvc.inventario.services;

import com.correa.msvc.inventario.exceptions.InventarioException;
import com.correa.msvc.inventario.models.Inventario;
import com.correa.msvc.inventario.repositories.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InventarioServiceImpl implements InventarioService{
    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public List<Inventario> findAll() {return inventarioRepository.findAll();}

    @Override
    public Inventario findById(Long id){
        return inventarioRepository.findById(id).orElseThrow(
                ()-> new InventarioException("No se encuentra registro de id"+id+"NO EXISTE")

        );
    }
    @Override
    public Inventario save(Inventario inventario){
        inventario.setCantidadInventario(
                inventario.getCantidadInventario()==null|| inventario.getCantidadInventario().trim().isEmpty()
                ?"0"
                        :inventario.getCantidadInventario()
        );
        return inventarioRepository.save(inventario);
    }
    @Override
    public void deleteById(Long id){inventarioRepository.deleteById(id);}

    @Override
    public Inventario update(Long id, Inventario inventario){
        return inventarioRepository.findById(id).map(Inventario ->{


        })
    }



}
