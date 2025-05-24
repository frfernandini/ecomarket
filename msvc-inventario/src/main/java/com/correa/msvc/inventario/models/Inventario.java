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

    @Column(nullable = false)
    @NotBlank(message = "El Campo de Cantidad no puede estar vacio")
    private String cantidadInventario;



    @Column(nullable = false)
    private Long stockId;

    @Column(nullable = false)
    @NotNull(message = "el campo de fecha de ingreso no puede estar vacio")
    private Date fechaIngresoProducto;


    @Column(nullable = false)
    private Long idProducto;


    @Column(nullable = false)
    private Long stockSucursal;

}
