package com.jcandia.msvc.ventas.msvc_ventas.services;

import com.jcandia.msvc.ventas.msvc_ventas.clients.ProductoClientsRest;
import com.jcandia.msvc.ventas.msvc_ventas.clients.SucursalClientsRest;
import com.jcandia.msvc.ventas.msvc_ventas.clients.UsuarioClientsRest;
import com.jcandia.msvc.ventas.msvc_ventas.dto.UsuarioVentasProductosDTO;
import com.jcandia.msvc.ventas.msvc_ventas.exceptions.VentaExceptions;
import com.jcandia.msvc.ventas.msvc_ventas.models.Producto;
import com.jcandia.msvc.ventas.msvc_ventas.models.Sucursal;
import com.jcandia.msvc.ventas.msvc_ventas.models.Usuarios;
import com.jcandia.msvc.ventas.msvc_ventas.models.entities.Ventas;
import com.jcandia.msvc.ventas.msvc_ventas.repository.VentaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService{

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoClientsRest productoClientsRest;
    @Autowired
    private SucursalClientsRest sucursalClientsRest;
    @Autowired
    private UsuarioClientsRest usuarioClientsRest;




    @Override
    public List<Ventas> findAll() {return ventaRepository.findAll();
    }

    @Override
    public Ventas findById(Long id) {
        return ventaRepository.findById(id).orElseThrow(
                () -> new VentaExceptions("La venta con id "+id+" no existe")
        );
    }

    @Override
    public Ventas save(Ventas ventas) {
        try{
            Sucursal sucursal = this.sucursalClientsRest.findById(ventas.getIdSucursal());

        } catch (FeignException exception) {
            throw new VentaExceptions("La SUCURSAL con id "+ventas.getIdSucursal()+" no existe,"+
                    "por ende no es posible generar el nexo de relacion");
        }
        try{
            Usuarios usuarios = this.usuarioClientsRest.findById(ventas.getIdUsuario());

        } catch (FeignException exception) {
            throw new VentaExceptions("El USUARIO con id "+ventas.getIdUsuario()+" no existe,"+
                    "por ende no es posible generar el nexo de relacion");
        }
        try{
            Producto producto = this.productoClientsRest.findById(ventas.getIdProducto());

        } catch (FeignException exception) {
            throw new VentaExceptions("El PRODUCTO con id "+ventas.getIdProducto()+" no existe,"+
                    "por ende no es posible generar el nexo de relacion");
        }
        return ventaRepository.save(ventas);
    }

    @Override
    public UsuarioVentasProductosDTO findByIdUsuario(Long idUsuario) {
        UsuarioVentasProductosDTO dto = new UsuarioVentasProductosDTO();
        dto.setUsuarios(usuarioClientsRest.findById(idUsuario));

        List<Producto> productos = this.ventaRepository.findByIdUsuario(idUsuario).stream().map(venta ->{
            return productoClientsRest.fi(venta.getProductos());
        }).toList();
        dto.setProductos(productos);
        return dto;
    }

}

//if(ventaRepository.findById(ventas.getIdVenta()).isPresent()){
        //throw new VentaExceptions("La venta con este ID ya esta registrado");
        //}