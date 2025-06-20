package com.cjohao.msvc.usuarios.msvc_usuarios.models;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Entidad que representa un usuario")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    @Schema(description = "primary key de usuario",example = "1")
    private Long idUsuario;

    @Column(name = "run_usuario",nullable = false,unique = true)
    @NotBlank(message = "El campo run no puede estar vacio")
    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El formato del run usuario debe ser XXXXXXXX-X")
    @Schema(description = "run de usuario",example = "12345678-9")
    private String run;

    @Column(name = "nombres_usuario",nullable = false)
    @NotBlank(message = "El campo nombres no puede estar vacio")
    @Schema(description = "nombre de usuario",example = "nombre segundonombre")
    private String nombresUsuario;

    @Column(name = "apellidos_usuario",nullable = false)
    @NotBlank(message = "El campo apellidos no puede estar vacio")
    @Schema(description = "apellidos del usuario",example = "apellidopaterno apellidomaterno")
    private String apellidosUsuario;

    @Column(name = "correo_usuario",nullable = false,unique = true)
    @Email(message = "el correo debe tener un formato correcto")
    @NotBlank(message = "El campo correo no puede estar vacio")
    @Schema(description = "correo del usuario",example = "correo@gmail.com")
    private String correoUsuario;

    @Column(name = "contraseña_usuario",nullable = false,unique = true)
    @NotBlank(message = "El campo contraseña no puede estar vacio")
    @Schema(description = "contraseña del usuario",example = "contraseñasegura")
    private String contraseña;

    @Column(name = "registro_usuario",nullable = false)
    @NotNull(message = "La fecha de registro no puede ser nula")
    @Schema(description = "fecha en la que se registro el usuario",example = "yyyy-MM-dd")
    private LocalDate registroUsuario;
}
