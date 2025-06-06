package com.correa.msvc.inventario.repositories;

import com.correa.msvc.inventario.models.entities.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findById(Long idInventario);
    Optional<Inventario> findByIdProducto(Long idProducto);
}
