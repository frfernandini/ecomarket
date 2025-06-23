package com.correa.msvc.inventario.controllers;


import com.correa.msvc.inventario.assembler.SucursalModelAssembler;
import com.correa.msvc.inventario.dtos.ErrorDTO;
import com.correa.msvc.inventario.exceptions.GlobalExceptionHandler;
import com.correa.msvc.inventario.models.Sucursal;
import com.correa.msvc.inventario.services.SucursalService;
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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v2/sucursales")
@Validated
@Tag(name = "sucursal API HATEOAS",description = "aqui se generan todos los metodos CRUD para sucursal")

public class SucursalControllerV2 {

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private SucursalModelAssembler sucursalModelAssembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Endpoint que obtiene todos las sucursales",
            description = "este endpoint devuelve en un list todos las sucursales que se encuentren en la base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "obtencion de todos las sucursales registradas exitosamente",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Sucursal.class)
                    ))
    })
    public ResponseEntity<CollectionModel<EntityModel<Sucursal>>> findAll() {
        List<EntityModel<Sucursal>> entities = sucursalService.findAll()
                .stream()
                .map(sucursalModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Sucursal>> collectionModel = CollectionModel.of(
                entities,
                linkTo(methodOn(SucursalControllerV2.class).findAll()).withSelfRel()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);

    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Endpoint que obtiene una sucursal por id",
            description = "Endpoint que va a devolver una sucursal.class al momento de buscarlo por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "obtencion por id correcta",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Sucursal.class)
                    )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error la sucursal con  id ingresada no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                            //examples = @ExampleObject(
                            //        name = "ERROR NOT FOUND",
                            //        value = "{\"status\":\"200\",\"error\":\"producto no encontrado\"}"
                            //)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(
                    name = "id",
                    description = "Primary key - entidad sucursal"
            )
    })
    public ResponseEntity<EntityModel<Sucursal>>  findById(@PathVariable Long id){
        EntityModel<Sucursal> entityModel = this.sucursalModelAssembler.toModel(
                this.sucursalService.findById(id)
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }
    @PostMapping
    @Operation(
            summary = "Endpoint guardado de una sucursal",
            description = "Endpoint que se permite guardar un elemento Sucursal.class dentro de nuestra base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "creacion exitosa"),
            @ApiResponse(responseCode = "404",description = "algun elemento de un msvc no se encuentra",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GlobalExceptionHandler.class)
                    )),
            @ApiResponse(responseCode = "409",description = "el elemento que intentas crear ya existe")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Estructura de datos que me permite realizar la creacion de una sucursal",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Sucursal.class)
            )
    )
    public ResponseEntity<Sucursal> save(@Valid @RequestBody Sucursal sucursal) {
        Sucursal saved = this.sucursalService.save(sucursal);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Endpoint modificacion de una sucursal",
            description = "Endpoint que me permite modificar la informacion de un elemento sucursal.class anteriormente registrado en la base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "sucursal modificada"),
            @ApiResponse(
                    responseCode = "404",
                    description = "algun elemento no se encuentra",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })

    public ResponseEntity<Sucursal> update(@PathVariable Long id, @Valid @RequestBody Sucursal sucursal) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.sucursalService.update(id,sucursal));
    }
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Endpoint borrar una sucursal",
            description = "Endpoint que me permite eliminar un elemento sucursal.class anteriormente guardado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "sucursal eliminada")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sucursalService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
