package com.correa.msvc.inventario.services;

import com.correa.msvc.inventario.exceptions.InventarioException;
import com.correa.msvc.inventario.models.Inventario;
import com.correa.msvc.inventario.repositories.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioServiceImpI implements InventarioService{
    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public List<Inventario> findAll(){return inventarioRepository.findAll();}



}
