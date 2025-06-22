package com.jcandia.msvc.ventas.msvc_ventas.services;





import com.jcandia.msvc.ventas.msvc_ventas.clients.InventarioClientsRest;
import com.jcandia.msvc.ventas.msvc_ventas.clients.ProductoClientsRest;
import com.jcandia.msvc.ventas.msvc_ventas.clients.SucursalClientsRest;
import com.jcandia.msvc.ventas.msvc_ventas.clients.UsuarioClientsRest;
import com.jcandia.msvc.ventas.msvc_ventas.exceptions.VentaExceptions;
import com.jcandia.msvc.ventas.msvc_ventas.models.entities.Ventas;
import com.jcandia.msvc.ventas.msvc_ventas.repository.VentaRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
public class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @Mock
    private InventarioClientsRest inventarioClientsRest;

    @Mock
    private ProductoClientsRest productoClientsRest;

    @Mock
    private SucursalClientsRest sucursalClientsRest;

    @Mock
    private UsuarioClientsRest usuarioClientsRest;

    @InjectMocks
    private VentaServiceImpl ventaService;

    private List<Ventas> ventasList = new ArrayList<>();
    private Ventas ventaPrueba;

    @BeforeEach
    public void setUp() {
        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i=0;i<100;i++){

            LocalDate fechadate = faker.timeAndDate().birthday();
            LocalDateTime fechatime = fechadate.atStartOfDay();
            Ventas venta = new Ventas();
            venta.setIdVenta((long) i+1);
            venta.setCantidadProductoVenta(faker.number().randomDigit());
            venta.setFechaHoraVenta(fechatime);
            venta.setIdProducto(faker.number().randomNumber());
            venta.setIdSucursal(faker.number().randomNumber());
            venta.setIdUsuario(faker.number().randomNumber());
            this.ventasList.add(venta);
        }
        this.ventaPrueba = this.ventasList.get(0);
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
}
