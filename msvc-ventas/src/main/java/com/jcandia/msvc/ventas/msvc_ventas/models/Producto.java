package com.jcandia.msvc.ventas.msvc_ventas.models;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    private Long productoId;
    private String nombreProducto;
    private Double precioProducto;
}
