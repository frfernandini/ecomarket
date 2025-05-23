package com.jcandia.msvc.ventas.msvc_ventas.services;

import com.jcandia.msvc.ventas.msvc_ventas.models.entities.Ventas;
import com.jcandia.msvc.ventas.msvc_ventas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService{

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<Ventas> findAll() {return ventaRepository.findAll();
    }

    @Override
    public Ventas findById(Long id) {
        return null;
    }

    @Override
    public Ventas save(Ventas ventas) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Ventas update(Long id, Ventas ventas) {
        return null;
    }
}
