package com.jcandia.msvc.ventas.msvc_ventas.services;

import com.jcandia.msvc.ventas.msvc_ventas.models.entities.DetalleVentas;
import com.jcandia.msvc.ventas.msvc_ventas.models.entities.Ventas;

import java.util.List;

public interface DetalleVentaService {
    List<DetalleVentas> findAll();
    List<DetalleVentas> findByVentaId(Long ventaId);
    DetalleVentas save(DetalleVentas detalleVentas);
    void deleteById(Long id);
    DetalleVentas update(Long id, DetalleVentas detalleVentas);
}
