package com.correa.msvc.inventario.assemblers;


import com.correa.msvc.inventario.controller.InventarioControllerV2;
import com.correa.msvc.inventario.models.entities.Inventario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<Inventario , EntityModel<Inventario>> {
    @Override
    public EntityModel<Inventario> toModel(Inventario entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(InventarioControllerV2.class).findById(entity.getInventarioId())).withSelfRel(),
                linkTo(methodOn(InventarioControllerV2.class).findAll()).withRel("inventarios"),
                Link.of("http://localhost:8085/api/v2/productos/" + entity.getInventarioId()).withRel("productos"),
                Link.of("http://localhost:8086/api/v2/sucursales"+ entity.getIdSucursal()).withRel("sucursales")
        );
    }

}

