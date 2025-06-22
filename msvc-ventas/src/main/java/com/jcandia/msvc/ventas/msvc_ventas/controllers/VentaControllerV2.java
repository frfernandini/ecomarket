package com.jcandia.msvc.ventas.msvc_ventas.controllers;

import com.jcandia.msvc.ventas.msvc_ventas.assemblers.ListaVentasUsuarioDTOModelAssembler;
import com.jcandia.msvc.ventas.msvc_ventas.assemblers.VentaModelAssembler;
import com.jcandia.msvc.ventas.msvc_ventas.dto.ErrorDTO;
import com.jcandia.msvc.ventas.msvc_ventas.dto.ListaVentasUsuarioDTO;
import com.jcandia.msvc.ventas.msvc_ventas.exceptions.GlobalHandlerException;
import com.jcandia.msvc.ventas.msvc_ventas.models.entities.Ventas;
import com.jcandia.msvc.ventas.msvc_ventas.services.VentaService;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/ventas")
@Validated
@Tag(
        name = "Venta API",
        description = "aqui se generan todos los metodos CRUD para venta"
)
public class VentaControllerV2 {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private VentaModelAssembler ventaModelAssembler;

    @Autowired
    private ListaVentasUsuarioDTOModelAssembler listaVentasUsuarioDTOModelAssembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(
            summary = "Endpoint que obtien todos las ventas",
            description = "Este endpoint devuelve en un list todos las ventas que se encuentren en la base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion de extracion de ventas exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Ventas.class)
                    )
            )
    })
    public ResponseEntity<CollectionModel<EntityModel<Ventas>>> findByAll() {
        List<EntityModel<Ventas>>entityModels = this.ventaService.findAll()
                .stream()
                .map(ventaModelAssembler::toModel)
                .toList();
        CollectionModel<EntityModel<Ventas>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(VentaControllerV2.class).findByAll()).withSelfRel()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);
    }

    @GetMapping(value = "/{id}",produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(
            summary = "Endpoint que devuelve una venta por id",
            description = "Endpoint que va a devolver una venta.class al momento de buscarlo por id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "obtencion por id correcta",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Ventas.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error cuando la venta por id no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(
                    name = "id",
                    description = "Primary key - entidad venta",
                    required = true
            )
    })
    public ResponseEntity<EntityModel<Ventas>> findById(@PathVariable Long id) {

        EntityModel<Ventas> entityModel = this.ventaModelAssembler.toModel(
                this.ventaService.findById(id)
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }


    @PostMapping
    @Operation(
            summary = "Endpoint guardado de venta",
            description = "Endpoint que permite guardar una venta dentro de la base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "creacion exitosa"),
            @ApiResponse(responseCode = "400",description = "hay un elemento faltante",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GlobalHandlerException.class)
            )),
            @ApiResponse(responseCode = "409",description = "el elemento que se intenta guarda ya existe")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Estructura de datos que permite realizar la creacion de una venta",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Ventas.class)
            )
    )
    public ResponseEntity<Ventas> save(@RequestBody Ventas venta) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ventaService.save(venta));
    }

    @GetMapping("/usuario/{id}")
    @Operation(
            summary = "Endpoint busqueda de ventas de un usuario",
            description = "Endpoint que devuelve la lista de ventas realizada por un usuario buscandolo por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "obtencion por id correcta"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error cuando no se encuentra el usuario con la id ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    public ResponseEntity<ListaVentasUsuarioDTO> findByIdUsuario(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ventaService.findByIdUsuario(id));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Endpoint borrar una venta",
            description = "Endpoint que permite eliminar una venta registrada en la base de datos mediante id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "venta eliminada")
    })
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        ventaService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Endpoint que modifica una venta",
            description = "Endpoint que permite modificar la informacion de una venta realizada mediante id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "venta modificada"),
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
    public ResponseEntity<Ventas> update(@PathVariable Long id, @Valid @RequestBody Ventas ventas) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ventaService.update(id,ventas));
    }
}
