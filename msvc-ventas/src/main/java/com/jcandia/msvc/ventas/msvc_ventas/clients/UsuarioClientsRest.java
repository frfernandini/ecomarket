package com.jcandia.msvc.ventas.msvc_ventas.clients;

import com.jcandia.msvc.ventas.msvc_ventas.models.Usuarios;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-usuarios", url = "localhost:8081/api/v1/usuarios")
public interface UsuarioClientsRest {

    @GetMapping("/{id}")
    Usuarios findById(@PathVariable Long id);
}
