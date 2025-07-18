package com.cjohao.msvc.usuarios.msvc_usuarios.controllers;


import com.cjohao.msvc.usuarios.msvc_usuarios.dtos.ErrorDTO;
import com.cjohao.msvc.usuarios.msvc_usuarios.models.Usuarios;
import com.cjohao.msvc.usuarios.msvc_usuarios.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.SeparatorUI;
import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
@Validated
@Tag(
        name = "usuario API",
        description = "aqui se generan todos los metodos CRUD para usuario"
)
public class UsuarioController {


    @Autowired
    public UsuarioService usuarioService;

    @GetMapping
    @Operation(
            summary = "Endpoint que obtiene todos los usuarios",
            description = "Este Endpoint devuelve una lista de todos los usuarios que se encuentras en la base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion de extraccion de usuarios exitosa"
            )
    })
    public ResponseEntity<List<Usuarios>> findByAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Endpoint que devuelve un usuario por id",
            description = "Endpoint que va a devolver un usuario.class al momento de buscarlo por id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "obtencion por id correcta"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error usuario no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(
                    name = "id",
                    description = "Primary key - entidad usuario",
                    required = true
            )
    })
    public ResponseEntity<Usuarios> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioService.findById(id));
    }

    @GetMapping("/run/{runUsuario}")
    @Operation(
            summary = "Endpoint que devuelve un usuario al buscarlo por id",
            description = "Endpoint que va a devolver un usuario.class al momento de buscarlo por run"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "obtencion por run correcta"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "error cuando el usuario con run ingresada no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(
                    name = "run",
                    description = "atributo run - entidad usuario",
                    required = true
            )
    })
    public ResponseEntity<Usuarios> findByRun(@PathVariable String runUsuario) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioService.findByRun(runUsuario));
    }

    @PostMapping
    @Operation(
            summary = "Endpoint guardado de un usuario",
            description = "Endpoint que permite guardar un elemento usuario.class dentro de la base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "gurdado exitoso"),
            @ApiResponse(responseCode = "400",description = "hay un elemento falta en la creacion del objeto",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDTO.class)
            )),
            @ApiResponse(responseCode = "409",description = "el elemento que intentas crear ya existe")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Estructura de datos que permite realizar la creacion de un proveedor",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Usuarios.class)
            )
    )

    public ResponseEntity<Usuarios> save(@Valid @RequestBody Usuarios usuarios) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.save(usuarios));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Endpoint que modifica un usuario",
            description = "Endpoint que permite modificar la informacion de un elemento usuario.class"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "usuario modificado"),
            @ApiResponse(
                    responseCode = "400",
                    description = "hay un elemento faltante en la creacion del objeto",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "500",
            description = "no se esta respetando un constraint o la estructura de los datos no es la correcta",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDTO.class)
            ))
    })
    @Parameters(value = {
            @Parameter(
                    name = "id",
                    description = "Primary key - entidad usuario",
                    required = true
            )
    })
    public ResponseEntity<Usuarios> update(@PathVariable Long id, @Valid @RequestBody Usuarios usuarios) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.update(id,usuarios));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Endpoint borrar un usuario",
            description = "Endpoint que me permite elminar un usuario.class anteriormente guardado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "usuario eliminado")
    })
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
