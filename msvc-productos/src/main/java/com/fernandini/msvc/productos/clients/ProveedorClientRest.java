package com.fernandini.msvc.productos.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "msvc-productos",url = "localhost:8082/api/v1/proveedores")
public interface ProveedorClientRest {

    @GetMapping
    List<Proveedor> findAll();
}
