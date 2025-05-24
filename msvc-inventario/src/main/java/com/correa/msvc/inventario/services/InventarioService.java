package com.correa.msvc.inventario.services;

import com.correa.msvc.inventario.models.Inventario;

import java.util.List;
import java.util.Optional;

public interface InventarioService {

    List<Inventario> findAll();

    Inventario findById(Long id);

    Inventario save(Inventario inventario);
    void delete(Long id);


}
