package com.fernandini.msvc.productos.models;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Proveedor {
    private Long id;

    private String nombre;

    private String direccion;

    private String telefono;

    private String correo;

    private LocalDate fechaIngreso;
}
