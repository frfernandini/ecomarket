package com.correa.msvc.inventario.controller;


import com.correa.msvc.inventario.assemblers.InventarioModelAssembler;
import com.correa.msvc.inventario.dtos.ErrorDTO;
import com.correa.msvc.inventario.exceptions.GlobalHandlerException;
import com.correa.msvc.inventario.models.entities.Inventario;
import com.correa.msvc.inventario.services.InventarioService;
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
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("api/v2/inventarios")
@Validated
@Tag(name = "inventario API HATEOAS",description = "aqui se generan todos los metodos CRUD para inventario")

public class InventarioControllerV2 {

    @Autowired
    private InventarioService service;

    @Autowired
    private InventarioModelAssembler inventarioModelAssembler;
    @Autowired
    private InventarioService inventarioService;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Endpoint que obtiene todos los Inventarios",
    description = "este endpoint devuelve en un list todos los inventarios que se encuentren en la base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "obtencion de todos los inventarios registrados exitosa",
            content = @Content(
                    mediaType = MediaTypes.HAL_JSON_VALUE,
                    schema = @Schema(implementation = Inventario.class)
            ))
    })
    public ResponseEntity<CollectionModel<EntityModel<Inventario>>> findAll() {
        List<EntityModel<Inventario>> entities = service.findAll()
                .stream()
                .map(inventarioModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Inventario>> collectionModel = CollectionModel.of(
                entities,
                linkTo(methodOn(InventarioControllerV2.class).findAll()).withSelfRel()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);

    }


    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Endpoint que obtiene un inventario por id",
            description = "Endpoint que va a devolver un inventario.class al momento de buscarlo por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "obtencion por id correcta",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Inventario.class)
                    )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error el producto con  id ingresada no existe",
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
                    description = "Primary key - entidad inventario"
            )
    })

    public ResponseEntity<EntityModel<Inventario>>  findById(@PathVariable Long id){
        EntityModel<Inventario> entityModel = this.inventarioModelAssembler.toModel(
                this.inventarioService.findById(id)
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }


    @PostMapping
    @Operation(
            summary = "Endpoint guardado de un inventario",
            description = "Endpoint que se permite guardar un elemento Inventario.class dentro de nuestra base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "creacion exitosa"),
            @ApiResponse(responseCode = "404",description = "algun elemento de un msvc no se encuentra",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GlobalHandlerException.class)
                    )),
            @ApiResponse(responseCode = "409",description = "el elemento que intentas crear ya existe")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Estructura de datos que me permite realizar la creacion de un inventario",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Inventario.class)
            )
    )

    public ResponseEntity<Inventario> save(@Valid @RequestBody Inventario inventario) {
        Inventario saved = this.inventarioService.save(inventario);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Endpoint modificacion de un inventario",
            description = "Endpoint que me permite modificar la informacion de un elemento inventario.class anteriormente registrado en la base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "inventario modificado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "algun elemento no se encuentra",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })

    public ResponseEntity<Inventario> update(@PathVariable Long id, @Valid @RequestBody Inventario inventario) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.inventarioService.update(id, inventario));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Endpoint borrar un inventario",
            description = "Endpoint que me permite eliminar un elemento inventario.class anteriormente guardado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "inventario eliminado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventarioService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }



}
