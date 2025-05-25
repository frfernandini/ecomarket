package com.jcandia.msvc.ventas.msvc_ventas.repository;

import com.jcandia.msvc.ventas.msvc_ventas.dto.UsuarioVentasProductosDTO;
import com.jcandia.msvc.ventas.msvc_ventas.models.Producto;
import com.jcandia.msvc.ventas.msvc_ventas.models.entities.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VentaRepository extends JpaRepository<Ventas, Long> {
    List<Ventas> findByIdUsuario(Long idUsuario);
}
