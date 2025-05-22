package com.correa.msvc.inventario.models;

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

    @Column(name = "Cantidad-Inventario",nullable = false)
    @NotBlank(message = "El Campo de Cantidad no puede estar vacio")
    private String cantidadInventario;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock")
    private Long stock_id;

    @Column(name = "producto_fecha_ingreso",nullable = false)
    @NotNull(message = "el campo de fecha de ingreso no puede estar vacio")
    private Date fecha_ingreso_producto;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Long stockSucursal;

}
