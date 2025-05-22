package com.cjohao.msvc.usuarios.msvc_usuarios.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String run;
    private String nombresUsuario;
    private String apellidosUsuario;
    private String correoUsuario;
    private String contraseña;
    private LocalDate registroUsuario;
}
