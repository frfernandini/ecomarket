package com.jcandia.msvc.ventas.msvc_ventas.models;


import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Inventario {
    private Integer cantidadInventario;
    private Long idProducto;
}
