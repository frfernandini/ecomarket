package com.correa.msvc.inventario.controller;

import com.correa.msvc.inventario.dtos.ErrorDTO;
import com.correa.msvc.inventario.models.entities.Inventario;
import com.correa.msvc.inventario.services.InventarioService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventarios")
@Tag(name = "inventarios",description = "operaciones relacionadas con el inventario")
@Validated
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    @Operation(summary = "Obtener todos los Inventarios ", description = "Obtiene una lista de todos los inventarios ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación de extracción de Inventarios exitosa"
            )
    })
    public ResponseEntity<List<Inventario>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventarioService.findAll());
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Endpoint que devuelve un Inventario por ID",
            description = "Endpoint que devuelve un Inventario.class al momento de buscarlo por ID "
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obtención por ID correcta"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error cuando El Inventario con cierto ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    public ResponseEntity<Inventario> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventarioService.findById(id));
    }
    @PostMapping
    @Operation(
            summary = "Endpoint guardado de un Inventario",
            description = "Endpoint que permite capturar un elemento Inventario.class y lo guarda"+
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
            description = "Estructura de datos que permite realizar la creación de un inventario",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Inventario.class)
            )
    )
    public ResponseEntity<Inventario>save(@Valid @RequestBody Inventario inventario){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.save(inventario));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Inventario ", description = "Elimina un Inventario por su código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id){
        inventarioService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/descontar-stock/{idProducto}")
    @Operation(summary = "descontar un inventario", description = "Descuenta un inventario po su código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Inventario descontado exitosamente"),
            @ApiResponse(responseCode = "404",description = "Inventario no encontrado")
    })
    public ResponseEntity<Inventario> descontarStock(@PathVariable Long idProducto, @Valid @RequestBody Integer cantidadVentas) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventarioService.descontarCantidad(idProducto, cantidadVentas));
    }

}
