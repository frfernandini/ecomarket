package com.ecomarket.msvc_proveedores.assemblers;

import com.ecomarket.msvc_proveedores.controllers.ProveedorControllerV2;
import com.ecomarket.msvc_proveedores.models.Proveedor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<Proveedor, EntityModel<Proveedor>> {
    @Override
    public EntityModel<Proveedor> toModel(Proveedor entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(ProveedorControllerV2.class).findById(entity.getProveedoId())).withSelfRel(),
                linkTo(methodOn(ProveedorControllerV2.class).findAll()).withRel("proveedores"),
                Link.of("http://localhost:8085/api/v2/productos/" + entity.getProveedoId()).withRel("productos")

        );
    }
}
