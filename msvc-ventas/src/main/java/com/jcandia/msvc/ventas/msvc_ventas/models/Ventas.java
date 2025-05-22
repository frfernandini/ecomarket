package com.jcandia.msvc.ventas.msvc_ventas.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Setter @Getter @ToString
@AllArgsConstructor @NoArgsConstructor
@Table(name = "ventas")

public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;

    @Column(name = "fecha_venta")
    @NotNull(message = "El campo fecha de venta no puede estar vacio")
    private LocalDateTime fechaHoraVenta;

    //@Column(name = "id_sucursal")
    //@NotBlank
}
