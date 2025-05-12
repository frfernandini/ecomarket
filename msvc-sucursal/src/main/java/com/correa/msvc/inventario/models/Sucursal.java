package com.correa.msvc.inventario.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Sucursal_id")
    private Long Sucursal_id;

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
    private LocalDateTime horario;

}
