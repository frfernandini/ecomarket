package com.jcandia.msvc.ventas.msvc_ventas.assemblers;


import com.jcandia.msvc.ventas.msvc_ventas.controllers.VentaControllerV2;
import com.jcandia.msvc.ventas.msvc_ventas.dto.ListaVentasUsuarioDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class ListaVentasUsuarioDTOModelAssembler implements RepresentationModelAssembler<ListaVentasUsuarioDTO, EntityModel<ListaVentasUsuarioDTO>> {
    @Override
    public EntityModel<ListaVentasUsuarioDTO> toModel(ListaVentasUsuarioDTO entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(VentaControllerV2.class).findByIdUsuario(entity.getUsuarios().getIdUsuario())).withRel("ventas-usuario")
        );
    }
}
