package com.jcandia.msvc.ventas.msvc_ventas.services;

import com.jcandia.msvc.ventas.msvc_ventas.models.entities.DetalleVentas;

import java.util.List;

public interface DetalleVentaService {
    List<DetalleVentas> findAll();
    List<DetalleVentas> findByVentaId(Long ventaId);
    void deleteById(Long id);
    DetalleVentas update(Long id, DetalleVentas detalleVentas);
}
