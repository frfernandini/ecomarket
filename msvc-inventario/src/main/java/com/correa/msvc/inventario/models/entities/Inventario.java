package com.correa.msvc.inventario.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Getter @Setter @ToString @AllArgsConstructor
@NoArgsConstructor
@Table(name = "Inventario")

public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventario_id")
    private Long inventarioId;

    @Column(name = "cantidad_inventario",nullable = false)
    @NotNull(message = "El Campo de Cantidad no puede estar vacio")
    private Long cantidadInventario;

    @Column(name="fecha_ingreso_producto",nullable = false)
    @NotNull(message = "el campo de fecha de ingreso no puede estar vacio")
    private Date fechaIngresoProducto;


    @Column(name = "id_producto",nullable = false)
    @NotNull(message = "id de producto no puede estar vacio")
    private Long idProducto;

    @Column(name ="id_sucursal",nullable = false)
    @NotNull(message = "id surcursal no puede estar vacio")
    private Long idSucursal;

}
