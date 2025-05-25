package com.jcandia.msvc.ventas.msvc_ventas.clients;

import com.jcandia.msvc.ventas.msvc_ventas.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-productos", url = "localhost:8085/api/v1/productos")
public interface ProductoClientsRest {

    @GetMapping("/{id}")
    Producto findById(@PathVariable Long id);
}
