package com.correa.msvc.inventario.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Producto {
    private Long productoId;
    private String nombreProducto;
    private String descProducto;
    private double precioProducto;
    private String categoriaProducto;
    private Long proveedorId;

}
