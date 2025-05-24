package com.correa.msvc.inventario.clients;

import com.correa.msvc.inventario.models.Sucursal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-sucursal",url = "localhost:8086/api/v1/sucursales")
public interface SucursalClientRest {
    @GetMapping("/{id}")
    Sucursal findById(@PathVariable Long id);
}
