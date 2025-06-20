package com.ecomarket.msvc_proveedores.models;


import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Entidad que representa un proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proveedor_id")
    @Schema(description = "primary key de proveedor",example = "1")
    private Long proveedoId;

    @Column(name = "proveedor_nombre",nullable = false,unique = true)
    @NotBlank(message = "el campo del nombre no puede estar vacio")
    @Schema(description = "nombre proveedor",example = "proveedor 1")
    private String nombre;

    @Column(name = "proveedor_direccion",nullable = false)
    @NotBlank(message = "el campo de direccion no puede estar vacio")
    @Schema(description = "direccion de proveedor",example = "direccion 1")
    private String direccion;

    @Column(name = "proveedor_telefono",nullable = false)
    @NotBlank(message = "el campo de telefono no puede estar vacio")
    @Schema(description = "telefono proveedor",example = "9123456")
    private String telefono;

    @Column(name = "proveedor_correo")
    @Email(message = "el correo debe tener formato de correo")
    @Schema(description = "correo proveedor",example = "correo@gmail.com")
    private String correo;

    @Column(name = "proveedor_fecha_ingreso",nullable = false)
    @NotNull(message = "el campo de fecha de ingreso no puede estar vacio")
    @Schema(description = "fecha de ingreso de proveedor",example = "yyyy-MM-dd")
    private LocalDate fechaIngreso;


}
