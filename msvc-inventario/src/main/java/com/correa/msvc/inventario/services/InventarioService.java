package com.correa.msvc.inventario.services;

import com.correa.msvc.inventario.models.entities.Inventario;

import java.util.List;

public interface InventarioService {

    List<Inventario> findAll();

    Inventario findById(Long id);

    Inventario save(Inventario inventario);
    void delete(Long id);


}
