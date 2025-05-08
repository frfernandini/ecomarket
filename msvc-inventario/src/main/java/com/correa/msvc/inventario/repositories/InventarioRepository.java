package com.correa.msvc.inventario.repositories;

import com.correa.msvc.inventario.models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

}
