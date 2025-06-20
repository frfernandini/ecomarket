package com.cjohao.msvc.usuarios.msvc_usuarios.assemblers;


import com.cjohao.msvc.usuarios.msvc_usuarios.controllers.UsuarioControllerV2;
import com.cjohao.msvc.usuarios.msvc_usuarios.models.Usuarios;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuarios, EntityModel<Usuarios>> {
    @Override
    public EntityModel<Usuarios> toModel(Usuarios entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(UsuarioControllerV2.class).findById(entity.getIdUsuario())).withSelfRel(),
                linkTo(methodOn(UsuarioControllerV2.class).findByRun(entity.getRun())).withSelfRel(),
                linkTo(methodOn(UsuarioControllerV2.class).findByAll()).withRel("usuarios")


        );
    }
}
