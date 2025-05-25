package com.jcandia.msvc.ventas.msvc_ventas.clients;

import com.jcandia.msvc.ventas.msvc_ventas.models.Inventario;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-inventario", url = "localhost:8089/api/v1/inventarios")
public interface InventarioClientsRest {

    @PutMapping("/descontar-stock/{idProducto}")
    Inventario descontarStock(@PathVariable Long idProducto, @Valid @RequestBody Integer cantidadVentas);
}
