package com.correa.msvc.inventario.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Sucursal {
    private Long sucursalId;
    private String tienda;
    private String direccion;
    private String telefono;
    private String horarioApertura;
    private String horarioCerrar;
}
