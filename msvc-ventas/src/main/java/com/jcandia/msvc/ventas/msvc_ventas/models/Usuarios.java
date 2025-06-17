package com.jcandia.msvc.ventas.msvc_ventas.models;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Usuarios {

    private Long idUsuario;
    private String run;
    private String nombresUsuario;
    private String apellidosUsuario;
    private String correoUsuario;
}
