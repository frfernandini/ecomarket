package com.jcandia.msvc.ventas.msvc_ventas.services;





import com.jcandia.msvc.ventas.msvc_ventas.clients.InventarioClientsRest;
import com.jcandia.msvc.ventas.msvc_ventas.clients.ProductoClientsRest;
import com.jcandia.msvc.ventas.msvc_ventas.clients.SucursalClientsRest;
import com.jcandia.msvc.ventas.msvc_ventas.clients.UsuarioClientsRest;
import com.jcandia.msvc.ventas.msvc_ventas.dto.ListaVentasUsuarioDTO;
import com.jcandia.msvc.ventas.msvc_ventas.dto.VentasProductosDetallesDTO;
import com.jcandia.msvc.ventas.msvc_ventas.exceptions.VentaExceptions;
import com.jcandia.msvc.ventas.msvc_ventas.models.Producto;
import com.jcandia.msvc.ventas.msvc_ventas.models.Sucursal;
import com.jcandia.msvc.ventas.msvc_ventas.models.Usuarios;
import com.jcandia.msvc.ventas.msvc_ventas.models.entities.Ventas;
import com.jcandia.msvc.ventas.msvc_ventas.repository.VentaRepository;
import feign.FeignException;
import feign.Request;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
public class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @Mock
    private InventarioClientsRest inventarioClientRest;

    @Mock
    private ProductoClientsRest productoClientRest;

    @Mock
    private SucursalClientsRest sucursalClientRest;

    @Mock
    private UsuarioClientsRest usuarioClientRest;

    @InjectMocks
    private VentaServiceImpl ventaService;


    private List<Ventas> ventasList = new ArrayList<>();
    private Ventas ventaPrueba;
    private Producto productoPrueba;
    private Sucursal sucursalPrueba;
    private Usuarios usuarioPrueba;


    @BeforeEach
    public void setUp() {
        Faker faker = new Faker(Locale.of("es","CL"));

        productoPrueba = new Producto();
        productoPrueba.setProductoId(2L);
        productoPrueba.setNombreProducto(faker.commerce().productName());
        productoPrueba.setPrecioProducto(Double.valueOf(faker.commerce().price().replace(",",".")));
        productoPrueba.setDescProducto(faker.commerce().material());
        productoPrueba.setProveedorId(1L);
        productoPrueba.setCategoriaProducto(faker.commerce().department());

        sucursalPrueba = new Sucursal();
        sucursalPrueba.setSucursalId(10L);
        sucursalPrueba.setTienda(faker.commerce().department() + " " + faker.address().cityName());
        sucursalPrueba.setTelefono("+56 9" + faker.number().numberBetween(1000, 9999) + " " + faker.number().numberBetween(1000, 9999));
        sucursalPrueba.setHorarioApertura(faker.number().numberBetween(8, 10) + ":00");
        sucursalPrueba.setHorarioCerrar(faker.number().numberBetween(20, 22) + ":00");

        usuarioPrueba = new Usuarios();
        usuarioPrueba.setIdUsuario(23L);
        usuarioPrueba.setNombresUsuario(faker.name().firstName());
        usuarioPrueba.setApellidosUsuario(faker.name().lastName());
        usuarioPrueba.setCorreoUsuario(faker.internet().emailAddress());
        usuarioPrueba.setContraseña(faker.internet().password());

        String numeroString = faker.idNumber().valid().replaceAll("-","");
        String ultimo = numeroString.substring(numeroString.length()-1);
        String restante = numeroString.substring(0,numeroString.length()-1);

        usuarioPrueba.setRun(restante+"-"+ultimo);
        for(int i=0;i<100;i++){

            LocalDate fechadate = faker.timeAndDate().birthday();
            LocalDateTime fechatime = fechadate.atStartOfDay();
            Ventas venta = new Ventas();
            venta.setIdVenta((long) i+1);
            venta.setCantidadProductoVenta(faker.number().randomDigit());
            venta.setFechaHoraVenta(fechatime);
            venta.setIdProducto(2L);
            venta.setIdSucursal(10L);
            venta.setIdUsuario(23L);
            this.ventasList.add(venta);
        }
        this.ventaPrueba = this.ventasList.get(0);
    }



    @Test
    @DisplayName("debe crear una venta")
    public void shoudlCreateVenta(){
        when(sucursalClientRest.findById(ventaPrueba.getIdSucursal())).thenReturn(this.sucursalPrueba);
        when(productoClientRest.findById(ventaPrueba.getIdProducto())).thenReturn(this.productoPrueba);
        when(usuarioClientRest.findById(ventaPrueba.getIdUsuario())).thenReturn(this.usuarioPrueba);
        doNothing().when(inventarioClientRest).descontarStock(ventaPrueba.getIdProducto(), ventaPrueba.getCantidadProductoVenta());
        when(ventaRepository.save(any(Ventas.class))).thenReturn(this.ventaPrueba);

        Ventas result = ventaService.save(this.ventaPrueba);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.ventaPrueba);
        assertThat(result.getIdProducto()).isEqualTo(productoPrueba.getProductoId());
        assertThat(result.getIdSucursal()).isEqualTo(sucursalPrueba.getSucursalId());
        assertThat(result.getIdUsuario()).isEqualTo(usuarioPrueba.getIdUsuario());

        verify(productoClientRest, times(1)).findById(ventaPrueba.getIdProducto());
        verify(sucursalClientRest, times(1)).findById(ventaPrueba.getIdSucursal());
        verify(usuarioClientRest, times(1)).findById(ventaPrueba.getIdUsuario());
        verify(inventarioClientRest,times(1)).descontarStock(ventaPrueba.getIdProducto(), ventaPrueba.getCantidadProductoVenta());
        verify(ventaRepository, times(1)).save(any(Ventas.class));
    }

    @Test
    @DisplayName("Devuelve todas las ventas")
    public void shouldFindAllVentas() {
        when(ventaRepository.findAll()).thenReturn(this.ventasList);
        List<Ventas> result = ventaService.findAll();
        assertThat(result).hasSize(100);
        assertThat(result).contains(this.ventaPrueba);

        verify(ventaRepository, times(1)).findAll();
    }


    @Test
    @DisplayName("Encontrar por id una venta")
    public void shouldFindVentaById() {
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(ventaPrueba));
        Ventas result = ventaService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.ventaPrueba);

        verify(ventaRepository, times(1)).findById(1L);
    }


    @Test
    @DisplayName("encontrar por id una venta que no existe")
    public void shouldNotFindVentaById() {
        Long idInexistente = 1L;
        when(ventaRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            ventaService.findById(idInexistente);
        }).isInstanceOf(VentaExceptions.class)
                .hasMessageContaining("La venta con id "+idInexistente+" no existe");
        verify(ventaRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("deberia guardar una venta")
    public void shouldSaveVenta() {
        when(ventaRepository.save(any(Ventas.class))).thenReturn(this.ventaPrueba);
        Ventas result = ventaService.save(this.ventaPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.ventaPrueba);
        verify(ventaRepository, times(1)).save(any(Ventas.class));
    }

    @Test
    @DisplayName("debe fallar cuando el producto no existe")
    public void shouldFailWhenProductoNotExists() {
        Long productoIdInexistente = 999L;
        ventaPrueba.setIdProducto(productoIdInexistente);

        when(productoClientRest.findById(productoIdInexistente))
                .thenThrow(new FeignException.NotFound("Not found",
                        Request.create(Request.HttpMethod.GET, "/productos/" + productoIdInexistente,
                                Collections.emptyMap(), null, null, null),
                        null, null));

        assertThatThrownBy(() -> ventaService.save(ventaPrueba))
                .isInstanceOf(VentaExceptions.class)
                .hasMessageContaining("PRODUCTO")
                .hasMessageContaining(String.valueOf(productoIdInexistente));
    }
    @Test
    @DisplayName("debe encontrar ventas por usuario con detalles")
    public void shouldFindVentasByUsuarioWithDetails() {
        Long usuarioId = 23L;
        ventaPrueba.setIdUsuario(usuarioId);

        when(usuarioClientRest.findById(usuarioId)).thenReturn(usuarioPrueba);
        when(ventaRepository.findByIdUsuario(usuarioId)).thenReturn(List.of(ventaPrueba));
        when(productoClientRest.findById(ventaPrueba.getIdProducto())).thenReturn(productoPrueba);

        ListaVentasUsuarioDTO result = ventaService.findByIdUsuario(usuarioId);

        assertThat(result).isNotNull();
        assertThat(result.getUsuarios()).isEqualTo(usuarioPrueba);
        assertThat(result.getVentas())
                .hasSize(1)
                .first()
                .extracting(
                        VentasProductosDetallesDTO::getProducto,
                        VentasProductosDetallesDTO::getIdVenta
                )
                .containsExactly(productoPrueba, ventaPrueba.getIdVenta());
    }
}
