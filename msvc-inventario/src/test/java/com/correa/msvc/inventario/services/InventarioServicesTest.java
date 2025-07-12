package com.correa.msvc.inventario.services;

import com.correa.msvc.inventario.clients.ProductoClientRest;
import com.correa.msvc.inventario.clients.SucursalClientRest;
import com.correa.msvc.inventario.exceptions.InventarioException;
import com.correa.msvc.inventario.models.Producto;
import com.correa.msvc.inventario.models.Sucursal;
import com.correa.msvc.inventario.models.entities.Inventario;
import com.correa.msvc.inventario.repositories.InventarioRepository;
import feign.FeignException;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class InventarioServicesTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @Mock
    private ProductoClientRest productoClientRest;

    @Mock
    private SucursalClientRest sucursalClientRest;

    @InjectMocks
    private InventarioServicelmpl inventarioService;

    private List<Inventario> inventarioList = new ArrayList<>();

    private Inventario inventarioPrueba;

    private Producto productoPrueba;
    private Sucursal sucursalPrueba;

    @BeforeEach
    public void setUp(){
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
        for(int i=0;i<100;i++){

            Inventario inventario = new Inventario();
            LocalDate fecha = faker.timeAndDate().birthday();
            inventario.setInventarioId((long)i+1);
            inventario.setCantidadInventario(faker.number().numberBetween(1,1000));
            inventario.setFechaIngresoProducto(fecha);
            inventario.setIdProducto(2L);
            inventario.setIdSucursal(10L);
            this.inventarioList.add(inventario);
        }
        this.inventarioPrueba= this.inventarioList.get(0);
    }


    @Test
    @DisplayName("debe crear un inventario")
    public void shoudlCreateInventario(){
        when(sucursalClientRest.findById(inventarioPrueba.getIdSucursal())).thenReturn(this.sucursalPrueba);
        when(productoClientRest.findById(inventarioPrueba.getIdProducto())).thenReturn(this.productoPrueba);
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(this.inventarioPrueba);

        Inventario result = inventarioService.save(this.inventarioPrueba);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.inventarioPrueba);
        assertThat(result.getIdProducto()).isEqualTo(productoPrueba.getProductoId());
        assertThat(result.getIdSucursal()).isEqualTo(sucursalPrueba.getSucursalId());

        verify(productoClientRest, times(1)).findById(inventarioPrueba.getIdProducto());
        verify(sucursalClientRest, times(1)).findById(inventarioPrueba.getIdSucursal());
        verify(inventarioRepository, times(1)).save(any(Inventario.class));
    }


    @Test
    @DisplayName("devuelve todos los inventarios")
    public void shouldFindAll(){
        when(inventarioRepository.findAll()).thenReturn(this.inventarioList);
        List<Inventario> result = inventarioService.findAll();
        assertThat(result).hasSize(100);
        assertThat(result).contains(this.inventarioPrueba);

        verify(inventarioRepository, times(1)).findAll();
    }
    @Test
    @DisplayName("Encontrar un inventario por ID")
    public void shouldFindInventarioById(){
        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(inventarioPrueba));
        Inventario result= inventarioService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.inventarioPrueba);

        verify(inventarioRepository, times(1)).findById(1L);




    }
    @Test
    @DisplayName("Encontrar por id un Inventario que no existe")
    public void shouldNotFindInventarioById(){
        Long idInexistente= 1L;
       when(inventarioRepository.findById(idInexistente)).thenReturn(Optional.empty());

       assertThatThrownBy(() ->  {
           inventarioService.findById(idInexistente);
       }).isInstanceOf(InventarioException.class)
               .hasMessageContaining("No se encontro el id "+idInexistente+" en la base de datos");
       verify(inventarioRepository, times(1)).findById(idInexistente);

    }

    @Test
    @DisplayName("Deberia Guardar un Inventario")
    public void shouldSave(){
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventarioPrueba);
        Inventario result= inventarioService.save(inventarioPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.inventarioPrueba);
        verify(inventarioRepository, times(1)).save(any(Inventario.class));
    }
    @Test
    @DisplayName("debe actualizar inventario correctamente")
    public void shouldUpdateInventario() {
        Inventario inventarioActualizado = new Inventario();
        inventarioActualizado.setIdProducto(2L);
        inventarioActualizado.setIdSucursal(10L);
        inventarioActualizado.setCantidadInventario(50);
        inventarioActualizado.setFechaIngresoProducto(LocalDate.now());

        when(productoClientRest.findById(2L)).thenReturn(productoPrueba);
        when(sucursalClientRest.findById(10L)).thenReturn(sucursalPrueba);
        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(inventarioPrueba));
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventarioActualizado);

        Inventario result = inventarioService.update(1L, inventarioActualizado);

        assertThat(result.getCantidadInventario()).isEqualTo(50);
        verify(inventarioRepository).save(any(Inventario.class));
    }
    @Test
    @DisplayName("debe descontar cantidad correctamente")
    public void shouldDescontarCantidad() {
        // 1. Configurar el estado inicial explícitamente
        int cantidadInicial = inventarioPrueba.getCantidadInventario();
        int cantidadADescontar = 10;
        int cantidadEsperada = inventarioPrueba.getCantidadInventario()-10;

        inventarioPrueba.setCantidadInventario(cantidadInicial);
        when(inventarioRepository.findByIdProducto(2L))
                .thenReturn(Optional.of(inventarioPrueba));
        when(inventarioRepository.save(any(Inventario.class)))
                .thenAnswer(invocation -> {
                    Inventario inv = invocation.getArgument(0);
                    assertThat(inv.getCantidadInventario()).isEqualTo(cantidadEsperada);
                    return inv;
                });
        Inventario result = inventarioService.descontarCantidad(2L, cantidadADescontar);

        assertThat(result.getCantidadInventario())
                .isEqualTo(cantidadEsperada);

        verify(inventarioRepository).findByIdProducto(2L);
        verify(inventarioRepository).save(inventarioPrueba);
    }

    @Test
    @DisplayName("debe fallar al descontar cantidad insuficiente")
    public void shouldFailWhenInsufficientStock() {
        inventarioPrueba.setCantidadInventario(5);
        when(inventarioRepository.findByIdProducto(2L))
                .thenReturn(Optional.of(inventarioPrueba));

        assertThatThrownBy(() -> inventarioService.descontarCantidad(2L, 10))
                .isInstanceOf(InventarioException.class)
                .hasMessageContaining("insuficiente");
    }
}
