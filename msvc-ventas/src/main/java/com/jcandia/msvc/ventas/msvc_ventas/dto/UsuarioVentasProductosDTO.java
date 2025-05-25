package com.jcandia.msvc.ventas.msvc_ventas.dto;

import com.jcandia.msvc.ventas.msvc_ventas.models.Producto;
import com.jcandia.msvc.ventas.msvc_ventas.models.Usuarios;
import com.jcandia.msvc.ventas.msvc_ventas.models.entities.Ventas;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioVentasProductosDTO {

    private Usuarios usuarios;
    private List<Producto> productos;
}
