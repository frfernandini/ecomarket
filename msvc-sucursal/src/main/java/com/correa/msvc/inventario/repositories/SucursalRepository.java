package com.correa.msvc.inventario.repositories;

import com.correa.msvc.inventario.models.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    Optional<Sucursal> findByTienda(String tienda);
}
