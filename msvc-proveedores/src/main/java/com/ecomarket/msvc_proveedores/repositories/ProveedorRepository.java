package com.ecomarket.msvc_proveedores.repositories;

import com.ecomarket.msvc_proveedores.models.Proveedor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProveedorRepository {

    Optional<Proveedor> findByNombre(long id);
}
