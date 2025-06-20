package com.fernandini.msvc.productos.models;


import ch.qos.logback.core.model.NamedModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos")
@Schema(description = "Entidad que representa un producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    @Schema(description = "priamary key de producto",example = "1")
    private Long productoId;

    @Column(name = "nombre_producto",nullable = false)
    @NotBlank(message = "el campo de nombre no puede estar vacio")
    @Schema(description = "nombre producto",example = "nombre producto")
    private String nombreProducto;

    @Column(name = "desc_producto",nullable = false)
    @NotBlank(message = "el campo de descripcion del producto no puede estar vacio")
    @Schema(description = "descripcion de un producto",example = "descripcion")
    private String descProducto;

    @Column(name = "precio_producto",nullable = false)
    @NotNull(message = "el campo del precio del producto no puede estar vacio")
    @Schema(description = "precio de un producto",example = "1000")
    private Double precioProducto;

    @Column(name = "categoria_producto",nullable = false)
    @NotBlank(message = "el campo de categoria del producto no puede estar vacio")
    @Schema(description = "nombre de la categoria que pertenece el producto",example = "tecnologia ")
    private String categoriaProducto;

    @Column(name = "proveedor_id",nullable = false)
    @NotNull(message = "el campo de id de proveedor no puede estar vacio")
    @Schema(description = "id del proveedor al que pertenece este producto",example = "1")
    private Long proveedorId;


    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescProducto() {
        return descProducto;
    }

    public void setDescProducto(String descProducto) {
        this.descProducto = descProducto;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }
}
