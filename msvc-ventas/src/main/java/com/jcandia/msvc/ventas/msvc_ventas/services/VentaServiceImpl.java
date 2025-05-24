package com.jcandia.msvc.ventas.msvc_ventas.services;

import com.jcandia.msvc.ventas.msvc_ventas.exceptions.VentaExceptions;
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
        return ventaRepository.findById(id).orElseThrow(
                () -> new VentaExceptions("La venta con id "+id+" no existe")
        );
    }

    @Override
    public Ventas save(Ventas ventas) {
        if(ventaRepository.findById(ventas.getIdVenta()).isPresent()){
            throw new VentaExceptions("La venta con este ID ya esta registrado");
        }
        return ventaRepository.save(ventas);
    }

    @Override
    public void deleteById(Long id) {ventaRepository.deleteById(id);}

    @Override
    public Ventas update(Long id, Ventas ventas) {
        return ventaRepository.findById(id).map(v->{
            v.setFechaHoraVenta(ventas.getFechaHoraVenta());
            v.setIdSucursal(ventas.getIdSucursal());
            v.setIdUsuario(ventas.getIdUsuario());
            return ventaRepository.save(v);
        }).orElseThrow(
                () -> new VentaExceptions("La venta con id "+id+" no existe")
        );
    }
}
