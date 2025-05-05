package com.ecomarket.msvc_proveedores.services;

import com.ecomarket.msvc_proveedores.models.Proveedor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {
    @Override
    public List<Proveedor> findAll() {
        return List.of();
    }

    @Override
    public Proveedor findById(Long id) {
        return null;
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Proveedor update(Long id, Proveedor proveedor) {
        return null;
    }
}
