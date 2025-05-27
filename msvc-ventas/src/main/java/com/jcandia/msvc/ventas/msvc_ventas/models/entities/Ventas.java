package com.jcandia.msvc.ventas.msvc_ventas.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ventas")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;

    @Column(name = "fecha_venta")
    @NotNull(message = "El campo fecha de venta no puede estar vacio")
    private LocalDateTime fechaHoraVenta;

    @Column(name = "id_sucursal",nullable = false)
    @NotNull(message = "El campo de id sucursal no puede estar vacio")
    private Long idSucursal;

    @Column(name = "id_usuario",nullable = false)
    @NotNull(message = "El campo de id usuario no puede estar vacio")
    private Long idUsuario;

    @Column(name = "id_producto",nullable = false)
    @NotNull(message = "El campo de id producto no puede estar vacio")
    private Long idProducto;

    @Column(name = "cantidad_producto",nullable = false)
    @NotNull(message = "El campo de cantidad de producto no puede estar vacio")
    private Integer cantidadProductoVenta;
}
