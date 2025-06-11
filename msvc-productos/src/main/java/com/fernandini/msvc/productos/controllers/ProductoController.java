package com.fernandini.msvc.productos.controllers;

import com.fernandini.msvc.productos.exceptions.GlobalHandlerException;
import com.fernandini.msvc.productos.exceptions.ProductoException;
import com.fernandini.msvc.productos.models.Producto;
import com.fernandini.msvc.productos.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.List;

@RestController
@RequestMapping("api/v1/productos")
@Validated
@Tag(name = "producto API",description = "aqio se generan todos los metodos CRUD para producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Endpoint que obtiene todos los productos",
    description = "este endpoint devuelve en un list todos los productos que se encuentren en la base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "obtencion de todos los productos registrados")
    })
    public ResponseEntity<List<Producto>> findAll(){
        List<Producto> productos = this.productoService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productos);
    }





    @GetMapping("/{id}")
    @Operation(summary = "Endpoint que obtiene un producor por id",
    description = "Endpoint que va a devolver un producto.class ak momento de buscarlo por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "obtencion por id correcta"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error cuando el producto con cierto id no existe",
                    content = @Content(
                            mediaType = "aplication/json",
                            // schema = @Schema(implementation = ProductoException.class)
                            examples = @ExampleObject(
                                    name = "ERROR NOT FOUND",
                                    value = "{\"status\":\"200\",\"error\":\"producto no encontrado\"}"
                            )
                    )
            )
    })
    @Parameters(value = {
            @Parameter(
                    name = "id",
                    description = "Primary key - entidad producto"
            )
    })

    public ResponseEntity<Producto> findById(@PathVariable Long id){
        Producto producto = this.productoService.findById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(producto);

    }





    @PostMapping
    @Operation(
            summary = "Endpoint guardado de un producto",
            description = "Endpoint que se permite acpturar un elemento Producto.class y lo guarda dentro de nuestra base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "creacion exitosa"),
            @ApiResponse(responseCode = "404",description = "algun elemtno de un msvc no se encuentra",
            content = @Content(
                    mediaType = "aplication/json",
                    schema = @Schema(implementation = GlobalHandlerException.class)
            )),
            @ApiResponse(responseCode = "409",description = "el elemento que intentas crear ya existe")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Estructura de datos que me permite realizar la creacion de un producto",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Producto.class)
            )
    )




    public ResponseEntity<Producto> save(@Valid @RequestBody Producto producto) {
        Producto saved = this.productoService.save(producto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @Valid @RequestBody Producto producto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.productoService.update(id, producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
