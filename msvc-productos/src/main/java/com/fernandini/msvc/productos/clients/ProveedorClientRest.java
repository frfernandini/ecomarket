package com.fernandini.msvc.productos.clients;



import com.fernandini.msvc.productos.models.Proveedor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@FeignClient(name = "msvc-productos",url = "localhost:8083/api/v1/proveedores")
public interface ProveedorClientRest {

    @GetMapping("/{id}")
    Proveedor findById(@PathVariable Long id);
}
