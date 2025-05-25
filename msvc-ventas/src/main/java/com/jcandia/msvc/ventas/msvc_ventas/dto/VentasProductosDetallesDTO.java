package com.jcandia.msvc.ventas.msvc_ventas.dto;

import com.jcandia.msvc.ventas.msvc_ventas.models.Producto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VentasProductosDetallesDTO {

    private Long idVenta;
    private LocalDateTime fechaHoraVenta;
    private Long idSucursal;
    private Long idUsuario;
    private Producto producto; // Producto embebido
    private Integer cantidadProductoVenta;
}
