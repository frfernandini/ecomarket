package com.jcandia.msvc.ventas.msvc_ventas.dto;

import com.jcandia.msvc.ventas.msvc_ventas.models.Usuarios;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ListaVentasUsuarioDTO {

    private Usuarios usuarios;
    private List<VentasProductosDetallesDTO> ventas;

}
