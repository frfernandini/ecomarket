package com.cjohao.msvc.usuarios.msvc_usuarios.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter @Getter @ToString
@AllArgsConstructor @NoArgsConstructor
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUsuario;

    @Column(name = "run_usuario",nullable = false,unique = true)
    @NotBlank(message = "El campo run no puede estar vacio")
    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El formato del run usuario debe ser XXXXXXXX-X")
    private String run;

    @Column(name = "nombres_usuario",nullable = false)
    @NotBlank(message = "El campo nombres no puede estar vacio")
    private String nombresUsuario;

    @Column(name = "apellidos_usuario",nullable = false)
    @NotBlank(message = "El campo apellidos no puede estar vacio")
    private String apellidosUsuario;

    @Column(name = "correo_usuario",nullable = false,unique = true)
    @Email(message = "el correo debe tener un formato correcto")
    @NotBlank(message = "El campo correo no puede estar vacio")
    private String correoUsuario;

    @Column(name = "contraseña_usuario",nullable = false,unique = true)
    @NotBlank(message = "El campo contraseña no puede estar vacio")
    private String contraseña;

    @Column(name = "registro_usuario",nullable = false)
    @NotNull(message = "La fecha de registro no puede ser nula")
    private LocalDate registroUsuario;
}
