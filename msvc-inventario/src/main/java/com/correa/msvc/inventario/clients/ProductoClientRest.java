package com.correa.msvc.inventario.clients;


import com.correa.msvc.inventario.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-productos",url = "localhost:8085/api/v1/productos")
public interface ProductoClientRest {
    @GetMapping("/{id}")
    Producto findById(@PathVariable Long id);
}
