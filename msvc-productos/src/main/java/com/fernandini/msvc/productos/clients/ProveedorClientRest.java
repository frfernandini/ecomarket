package com.fernandini.msvc.productos.clients;


import com.ecomarket.msvc_proveedores.models.Proveedor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-productos",url = "localhost:8082/api/v1/proveedores")
public interface ProveedorClientRest {

    @GetMapping
    List<Proveedor> findAll();

    @GetMapping("/{id}")
    Proveedor findById(@PathVariable Long id);
}
