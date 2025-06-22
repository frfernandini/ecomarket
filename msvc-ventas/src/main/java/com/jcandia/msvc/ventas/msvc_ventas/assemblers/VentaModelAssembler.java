package com.jcandia.msvc.ventas.msvc_ventas.assemblers;



import com.jcandia.msvc.ventas.msvc_ventas.controllers.VentaControllerV2;
import com.jcandia.msvc.ventas.msvc_ventas.models.entities.Ventas;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class VentaModelAssembler implements RepresentationModelAssembler<Ventas, EntityModel<Ventas>> {
    @Override
    public EntityModel<Ventas> toModel(Ventas entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(VentaControllerV2.class).findById(entity.getIdVenta())).withSelfRel(),
                linkTo(methodOn(VentaControllerV2.class).findByAll()).withRel("ventas"),
                Link.of("http://localhost:8086/api/v1/sucursales/" + entity.getIdSucursal()),
                Link.of("http://localhost:8081/api/v2/usuarios/" + entity.getIdUsuario()),
                Link.of("htttp://localhost:8085/api/v2/productos/" +entity.getIdProducto())
        );
    }
}
