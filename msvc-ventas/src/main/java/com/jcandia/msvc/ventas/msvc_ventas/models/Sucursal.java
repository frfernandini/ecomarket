package com.jcandia.msvc.ventas.msvc_ventas.models;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {

    private Long SucursalId;
    private String tienda;
    private String direccion;
    private String telefono;
    private String horarioApertura;
    private String horarioCerrar;
}
