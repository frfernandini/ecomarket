package com.fernandini.msvc.productos.models;


import ch.qos.logback.core.model.NamedModel;
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
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long productoId;

    @Column(name = "nombre_producto",nullable = false,unique = true)
    @NotBlank(message = "el campo de nombre no puede estar vacio")
    private String nombreProducto;

    @Column(name = "desc_producto",nullable = false)
    @NotBlank(message = "el campo de descripcion del producto no puede estar vacio")
    private String descProducto;

    @Column(name = "precio_producto",nullable = false)
    @NotNull(message = "el campo del precio del producto no puede estar vacio")
    private Double precioProducto;

    @Column(name = "categoria_producto",nullable = false)
    @NotBlank(message = "el campo de categoria del producto no puede estar vacio")
    private String categoriaProducto;

    @Column(name = "fecha_ingreso_producto",nullable = false)
    @NotNull(message = "el campo de fecha de ingreso no puede estar vacio")
    private Date fechaIngresoProducto;

    @Column(name = "proveedor_id",nullable = false)
    @NotNull(message = "el campo de id de proveedor no puede estar vacio")
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

    public Date getFechaIngresoProducto() {
        return fechaIngresoProducto;
    }

    public void setFechaIngresoProducto(Date fechaIngresoProducto) {
        this.fechaIngresoProducto = fechaIngresoProducto;
    }

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }
}
