package com.correa.msvc.inventario.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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

}
