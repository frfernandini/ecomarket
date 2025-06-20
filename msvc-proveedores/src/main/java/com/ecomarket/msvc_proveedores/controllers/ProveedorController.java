package com.ecomarket.msvc_proveedores.controllers;

import com.ecomarket.msvc_proveedores.dtos.ErrorDTO;
import com.ecomarket.msvc_proveedores.exceptions.GlobalExceptionHandler;
import com.ecomarket.msvc_proveedores.models.Proveedor;
import com.ecomarket.msvc_proveedores.services.ProveedorService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bouncycastle.jcajce.spec.RawEncodedKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.List;

@RestController
@RequestMapping("api/v1/proveedores")
@Validated
@Tag(
        name = "Proveedor API",
        description = "Aqui se generan todos los metodos CRUD para proveedor"
)
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    @Operation(
            summary = "Endpoint que obtiene todos los proveedores",
            description = "Este Endpoint devuelve en un list todos los proveedores que se encuentran en la base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion de extracion de proveedores exitosa"
            )
    })
    public ResponseEntity<List<Proveedor>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(proveedorService.findAll());
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Endpoint  que devuelve un proveedor por id",
            description = "Endpoint que va a devolver un proveedor.class al momento de buscarlo por id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "obtencion por id correcta"

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error cuando el proveedor con id ingresada no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(
                    name = "id",
                    description = "Primary key - entidad proveedor",
                    required = true
            )
    })
    public ResponseEntity<Proveedor> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(proveedorService.findById(id));
    }

    @PostMapping
    @Operation(
            summary = "Endpoint guardado de un proveedor",
            description = "Endpoint que  permite guardar un elemento proveedor.class dentro de nuestra base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "creacion exitosa"),
            @ApiResponse(responseCode = "400",description = "hay un elemento faltante en la creacion del objeto",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GlobalExceptionHandler.class)
            )),
            @ApiResponse(responseCode = "409",description = "el elemento que intentas crear ya existe")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Estructura de datos que  permite realizar la creacion de un proveedor",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Proveedor.class)
            )
    )
    public ResponseEntity<Proveedor> save(@Valid @RequestBody Proveedor proveedor) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(proveedorService.save(proveedor));
    }





    @PutMapping("/{id}")
    @Operation(
            summary = "Endpoint que modifica un proveedor",
            description = "Endpoint que permite modificar la informacion de un elemento proveedor.class anteriormente guardado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "proveedor modificado"),
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
                    description = "Primary key - entidad proveedor",
                    required = true
            )
    })
    public ResponseEntity<Proveedor> update(@PathVariable Long id, @Valid @RequestBody Proveedor proveedor) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(proveedorService.update(id, proveedor));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Endpoint borrar un proveedor",
            description = "Endpoint que me permite eliminar un proveedor.class anteriormente guardado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "proveedor eliminado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        proveedorService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
