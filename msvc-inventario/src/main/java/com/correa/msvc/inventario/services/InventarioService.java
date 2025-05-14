package com.correa.msvc.inventario.services;

import com.correa.msvc.inventario.models.Inventario;

import java.util.List;

public interface InventarioService {

    List<Inventario> findAll();
    Inventario findById(Long id);
    Inventario save(Inventario inventario);
    void deleteById (Long id);
    Inventario update(Long id,Inventario inventario);

}
