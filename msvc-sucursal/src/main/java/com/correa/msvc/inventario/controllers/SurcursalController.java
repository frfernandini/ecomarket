package com.correa.msvc.inventario.controllers;


import com.correa.msvc.inventario.dtos.ErrorDTO;
import com.correa.msvc.inventario.models.Sucursal;
import com.correa.msvc.inventario.services.SucursalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sucursales")
@Tag(name = "sucursales",description = "operaciones relacionadas con la sucursal")
@Validated
public class SurcursalController {
    @Autowired
    private SucursalService sucursalService;


    @GetMapping
    @Operation(summary = "Obtener todos las sucursales ", description = "Obtiene una lista de todas las sucursales ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación de extracción de Sucursales exitosa"
            )
    })
    public ResponseEntity<List<Sucursal>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sucursalService.findAll());
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Endpoint que devuelve una sucursal por ID",
            description = "Endpoint que devuelve una Sucusal.class al momento de buscarlo por ID "
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obtención por ID correcta"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error cuando La Sucursal con cierto ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    public ResponseEntity<Sucursal> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sucursalService.findById(id));
    }
    @PostMapping
    @Operation(
            summary = "Endpoint guardado de una Sucursal",
            description = "Endpoint que permite capturar un elemento Sucursal.class y lo guarda"+
                    "dentro de la base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Creacion exitosa"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Algun elemento de un msvc no se encuentra",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "El elemento que intentas crear ya existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Estructura de datos que permite realizar la creación de una sucursal",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Sucursal.class)
            )
    )
    public ResponseEntity<Sucursal> save(@Valid @RequestBody Sucursal sucursal) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sucursalService.save(sucursal));
    }
    @PutMapping("/{id}")
    @Operation(summary = "actualizar una Sucursal ", description = "actualiza una Sucursal por su código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    public ResponseEntity<Sucursal> update(@PathVariable Long id, @Valid @RequestBody Sucursal sucursal) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sucursalService.update(sucursal, id));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una Sucursal ", description = "Elimina una Sucursal por su código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sucursalService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
