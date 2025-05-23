package com.correa.msvc.inventario.services;

import com.correa.msvc.inventario.exceptions.SucursalException;
import com.correa.msvc.inventario.models.Sucursal;
import com.correa.msvc.inventario.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    @Override
    public Sucursal findById(Long id) {
        return sucursalRepository.findById(id).orElseThrow(
                () -> new SucursalException("No se encontro el sucursal con id: " + id)
        );
    }

    @Override
    public Sucursal save(Sucursal sucursal) {
        if(sucursalRepository.findByNombre(sucursal.getTienda()).isPresent()){
            throw new SucursalException("La tienda ya existe");
        }
        return sucursalRepository.save(sucursal);
    }

    @Override
    public void deleteById(Long id) {
        sucursalRepository.deleteById(id);
    }

    @Override
    public Sucursal update(Sucursal sucursal, Long id) {
        return sucursalRepository.findById(id).map(s -> {
            s.setDireccion( sucursal.getDireccion());
            s.setTelefono(sucursal.getTelefono());
            s.setTienda( sucursal.getTienda());
            s.setHorarioApertura( sucursal.getHorarioApertura());
            s.setHorarioCerrar( sucursal.getHorarioCerrar());
            return sucursalRepository.save(s);
        }).orElseThrow(
                () -> new SucursalException("No se encontro la sucursal")
        );
    }
}
