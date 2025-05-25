package com.jcandia.msvc.ventas.msvc_ventas.services;

import com.jcandia.msvc.ventas.msvc_ventas.clients.InventarioClientsRest;
import com.jcandia.msvc.ventas.msvc_ventas.clients.ProductoClientsRest;
import com.jcandia.msvc.ventas.msvc_ventas.clients.SucursalClientsRest;
import com.jcandia.msvc.ventas.msvc_ventas.clients.UsuarioClientsRest;
import com.jcandia.msvc.ventas.msvc_ventas.dto.UsuarioVentasProductosDTO;
import com.jcandia.msvc.ventas.msvc_ventas.dto.VentasProductosDetallesDTO;
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
    @Autowired
    private InventarioClientsRest inventarioClientsRest;




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
        try {
            inventarioClientsRest.descontarStock(ventas.getIdProducto(),ventas.getCantidadProductoVenta());
        } catch (FeignException e) {
            throw new VentaExceptions("No fue posible descontar stock: "+e.getMessage());
        }
        return ventaRepository.save(ventas);

    }

    @Override
    public UsuarioVentasProductosDTO findByIdUsuario(Long idUsuario) {
        UsuarioVentasProductosDTO dto = new UsuarioVentasProductosDTO();
        dto.setUsuarios(usuarioClientsRest.findById(idUsuario));

        List<Ventas> ventas = ventaRepository.findByIdUsuario(idUsuario);

        List<VentasProductosDetallesDTO> ventasConProductos = ventas.stream().map(venta -> {
            Producto producto = productoClientsRest.findById(venta.getIdProducto());
            VentasProductosDetallesDTO ventaDTO = new VentasProductosDetallesDTO();

            ventaDTO.setIdVenta(venta.getIdVenta());
            ventaDTO.setFechaHoraVenta(venta.getFechaHoraVenta());
            ventaDTO.setIdSucursal(venta.getIdSucursal());
            ventaDTO.setIdUsuario(venta.getIdUsuario());
            ventaDTO.setProducto(producto);
            ventaDTO.setCantidadProductoVenta(venta.getCantidadProductoVenta());

            return ventaDTO;
        }).toList();

        dto.setVentas(ventasConProductos);
        return dto;
    }

}

//if(ventaRepository.findById(ventas.getIdVenta()).isPresent()){
        //throw new VentaExceptions("La venta con este ID ya esta registrado");
        //}