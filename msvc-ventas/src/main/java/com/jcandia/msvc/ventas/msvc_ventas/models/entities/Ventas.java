package com.jcandia.msvc.ventas.msvc_ventas.models.entities;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "entidad que representa una venta")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    @Schema(description = "primary key de venta",example = "1")
    private Long idVenta;

    @Column(name = "fecha_venta")
    @NotNull(message = "El campo fecha de venta no puede estar vacio")
    @Schema(description = "fecha en la que se llevo a cabo la venta",example = "yyyy-MM-ddT00:00:00")
    private LocalDateTime fechaHoraVenta;

    @Column(name = "id_sucursal",nullable = false)
    @NotNull(message = "El campo de id sucursal no puede estar vacio")
    @Schema(description = "Foreign key de sucursal",example = "1")
    private Long idSucursal;

    @Column(name = "id_usuario",nullable = false)
    @NotNull(message = "El campo de id usuario no puede estar vacio")
    @Schema(description = "Foreign key de usuario",example = "1")
    private Long idUsuario;

    @Column(name = "id_producto",nullable = false)
    @NotNull(message = "El campo de id producto no puede estar vacio")
    @Schema(description = "Foreign key de producto",example = "1")
    private Long idProducto;

    @Column(name = "cantidad_producto",nullable = false)
    @NotNull(message = "El campo de cantidad de producto no puede estar vacio")
    @Schema(description = "cantidad del producto que se intenta comprar",example = "1")
    private Integer cantidadProductoVenta;
}
