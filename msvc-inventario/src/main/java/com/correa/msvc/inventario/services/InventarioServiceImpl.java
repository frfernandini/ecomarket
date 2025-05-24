package com.correa.msvc.inventario.services;

import com.correa.msvc.inventario.exceptions.InventarioException;
import com.correa.msvc.inventario.models.Inventario;
import com.correa.msvc.inventario.repositories.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class InventarioServiceImpl implements InventarioService{

    private final InventarioRepository inventarioRepository;

    @Autowired
    public InventarioServiceImpl(InventarioRepository inventarioRepository){
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Inventario> findAll(){
        return inventarioRepository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Inventario findById(Long id){
        return inventarioRepository.findById(id)
                .orElseThrow(()-> new InventarioException("No se encontró el registro con id:"+id));
    }

    @Override
    @Transactional
    public Inventario save(Inventario inventario){
        String cantidad = inventario.getCantidadInventario();
        inventario.setCantidadInventario(
                cantidad==null || cantidad.trim().isEmpty() ? "0" : cantidad.trim()
        );
        if (inventario.getFecha_ingreso_producto()==null){
            inventario.setFecha_ingreso_producto(new java.util.Date());
        }
        return inventarioRepository.save(inventario);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    @Transactional
    public Inventario update(Long id, Inventario inventario){
        return inventarioRepository.findById(id).map(inventarioExistente ->{
            inventarioExistente.setCantidadInventario(
                    inventario.getCantidadInventario()==null || inventario.getCantidadInventario().trim().isEmpty()
                    ? "0": inventario.getCantidadInventario().trim()
            );
            inventarioExistente.setFecha_ingreso_producto(
                    inventario.getFecha_ingreso_producto()!=null
                    ? inventario.getFecha_ingreso_producto()
                            :new java.util.Date()
            );
            return inventarioRepository.save(inventarioExistente);
        }).orElseThrow(()-> new InventarioException("No se encontró el registro con ID: "+id));
    }

}
