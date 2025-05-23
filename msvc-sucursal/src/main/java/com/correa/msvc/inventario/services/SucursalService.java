package com.correa.msvc.inventario.services;

import com.correa.msvc.inventario.models.Sucursal;

import java.util.List;

public interface SucursalService {
    List<Sucursal> findAll();
    Sucursal findById(Long id);
    Sucursal save(Sucursal sucursal);
    void deleteById(Long id);
    Sucursal update(Sucursal sucursal,Long id);
}
