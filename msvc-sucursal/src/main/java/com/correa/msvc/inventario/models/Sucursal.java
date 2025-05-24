package com.correa.msvc.inventario.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "sucursales")
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Sucursal_id")
    private Long SucursalId;

    @Column(name="nombre_tienda",nullable = false)
    @NotBlank(message = "El campo no puede quedar Vacio")
    private String tienda;

    @Column(name="direccion_tienda",nullable = false)
    @NotBlank(message = "El campo no puede quedar Vacio")
    private String direccion;


    @Column(name = "telefono_tienda",nullable = false)
    @NotBlank(message = "El campo no puede quedar Vacio")
    private String telefono;

    @Column(name="horario_apertura",nullable = false)
    @NotBlank(message = "El campo no puede quedar Vacio")
    private String horarioApertura;

    @Column(name = "horario_cerrar")
    @NotBlank(message = "el campo de hora de cerrar no puede estar vacio")
    private String horarioCerrar;
}
