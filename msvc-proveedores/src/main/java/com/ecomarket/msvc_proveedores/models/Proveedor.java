package com.ecomarket.msvc_proveedores.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "proveedores")
@Getter @Setter @ToString @NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proveedor_id")
    private Long proveedoId;

    @Column(name = "proveedor_nombre",nullable = false,unique = true)
    @NotBlank(message = "el campo del nombre no puede estar vacio")
    private String nombre;

    @Column(name = "proveedor_direccion",nullable = false)
    @NotBlank(message = "el campo de direccion no puede estar vacio")
    private String direccion;

    @Column(name = "proveedor_telefono",nullable = false)
    @NotBlank(message = "el campo de telefono no puede estar vacio")
    private String telefono;

    @Column(name = "proveedor_correo")
    @Email(message = "el correo debe tener formato de correo")
    private String correo;

    @Column(name = "proveedor_fecha_ingreso",nullable = false)
    @NotNull(message = "el campo de fecha de ingreso no puede estar vacio")
    private LocalDate fechaIngreso;


}
