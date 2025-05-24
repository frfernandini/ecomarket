package com.jcandia.msvc.ventas.msvc_ventas.services;

import com.jcandia.msvc.ventas.msvc_ventas.exceptions.VentaExceptions;
import com.jcandia.msvc.ventas.msvc_ventas.models.entities.DetalleVentas;
import com.jcandia.msvc.ventas.msvc_ventas.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService{

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public List<DetalleVentas> findAll() {return detalleVentaRepository.findAll();}

    @Override
    public List<DetalleVentas> findByVentaId(Long ventaId) {
        List<DetalleVentas> detalles = detalleVentaRepository.findByIdVenta(ventaId);
        if (detalles.isEmpty()) {
            throw new VentaExceptions("No se encontraron detalles para la venta con id " + ventaId);
        }
        return detalles;
    }

    @Override
    public void deleteById(Long id) {detalleVentaRepository.deleteById(id);}

    @Override
    public DetalleVentas update(Long id, DetalleVentas detalleVentas) {
        return detalleVentaRepository.findById(id).map(d ->{
            d.setProductoId(detalleVentas.getProductoId());
            d.setCantidad(detalleVentas.getCantidad());
            d.setPrecioUnitario(detalleVentas.getPrecioUnitario());
            return detalleVentaRepository.save(d);
        }).orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
    }

}
