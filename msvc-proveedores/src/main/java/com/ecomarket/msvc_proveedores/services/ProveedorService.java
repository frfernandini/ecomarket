package com.ecomarket.msvc_proveedores.services;

import com.ecomarket.msvc_proveedores.models.Proveedor;

import java.util.List;

public interface ProveedorService {

    List<Proveedor> findAll();
    Proveedor findById(Long id);
    Proveedor save(Proveedor proveedor);
    void deleteById(Long id);
    Proveedor update(Long id, Proveedor proveedor);
}
