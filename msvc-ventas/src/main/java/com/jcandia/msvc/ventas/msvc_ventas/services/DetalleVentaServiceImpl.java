package com.jcandia.msvc.ventas.msvc_ventas.services;

import com.jcandia.msvc.ventas.msvc_ventas.models.entities.DetalleVentas;

import java.util.List;

public class DetalleVentaServiceImpl implements DetalleVentaService{
    @Override
    public List<DetalleVentas> findAll() {
        return List.of();
    }

    @Override
    public List<DetalleVentas> findByVentaId(Long ventaId) {
        return List.of();
    }

    @Override
    public DetalleVentas save(DetalleVentas detalleVentas) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public DetalleVentas update(Long id, DetalleVentas detalleVentas) {
        return null;
    }
}
