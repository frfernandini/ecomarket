package com.ecomarket.msvc_proveedores.services;

import com.ecomarket.msvc_proveedores.exceptions.ProveedorException;
import com.ecomarket.msvc_proveedores.models.Proveedor;
import com.ecomarket.msvc_proveedores.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;
    @Override
    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor findById(Long id) {
        return proveedorRepository.findById(id).orElseThrow(
                () -> new ProveedorException("Proveedor con id: "+id +" no encontrado")
        );
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        if(proveedorRepository.findByNombre(proveedor.getNombre()).isPresent()){
            throw new ProveedorException("Proveedor ya existe");
        }
        return proveedorRepository.save(proveedor);
    }

    @Override
    public void deleteById(Long id) {
        proveedorRepository.deleteById(id);
    }

    @Override
    public Proveedor update(Long id, Proveedor proveedor) {
        return proveedorRepository.findById(id).map(p -> {
            p.setNombre(proveedor.getNombre());
            p.setCorreo(proveedor.getCorreo());
            p.setDireccion(proveedor.getDireccion());
            p.setTelefono(proveedor.getTelefono());
            p.setFechaIngreso(proveedor.getFechaIngreso());
            return proveedorRepository.save(p);
        }).orElseThrow(
                () -> new ProveedorException("Proveedor no encontrado")
        );
    }
}
