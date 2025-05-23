package com.jcandia.msvc.ventas.msvc_ventas.repository;

import com.jcandia.msvc.ventas.msvc_ventas.models.entities.DetalleVentas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVentas, Long> {
    Optional<DetalleVentas> findById(Long id);
}
