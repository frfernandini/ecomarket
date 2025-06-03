package com.jcandia.msvc.ventas.msvc_ventas.services;

import com.jcandia.msvc.ventas.msvc_ventas.dto.UsuarioVentasProductosDTO;
import com.jcandia.msvc.ventas.msvc_ventas.models.entities.Ventas;

import java.util.List;

public interface VentaService {
    List<Ventas> findAll();
    Ventas findById(Long id);
    Ventas save(Ventas ventas);
    UsuarioVentasProductosDTO findByIdUsuario(Long idUsuario);
    void deleteById(Long id);
}
