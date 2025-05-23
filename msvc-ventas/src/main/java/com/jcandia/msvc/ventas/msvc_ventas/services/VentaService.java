package com.jcandia.msvc.ventas.msvc_ventas.services;

import com.jcandia.msvc.ventas.msvc_ventas.models.entities.Ventas;

import java.util.List;

public interface VentaService {
    List<Ventas> findAll();
    Ventas findById(Long id);
    Ventas save(Ventas ventas);
    void deleteById(Long id);
    Ventas update(Long id,Ventas ventas);
}
