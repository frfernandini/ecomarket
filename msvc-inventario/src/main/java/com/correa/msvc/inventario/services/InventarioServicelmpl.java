package com.correa.msvc.inventario.services;

import com.correa.msvc.inventario.exceptions.InventarioException;
import com.correa.msvc.inventario.models.Inventario;
import com.correa.msvc.inventario.repositories.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventarioServicelmpl implements InventarioService{
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
    public Inventario save(Inventario inventario) {
        return this.inventarioRepository.save(inventario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        inventarioRepository.deleteById(id);

    }


}
