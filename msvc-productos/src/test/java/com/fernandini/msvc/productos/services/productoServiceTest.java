package com.fernandini.msvc.productos.services;


import com.fernandini.msvc.productos.clients.ProveedorClientRest;
import com.fernandini.msvc.productos.exceptions.ProductoException;
import com.fernandini.msvc.productos.models.Producto;
import com.fernandini.msvc.productos.models.Proveedor;
import com.fernandini.msvc.productos.repositories.ProductoRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class productoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ProveedorClientRest proveedorClientRest;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private List<Producto> productoList = new ArrayList<>();

    private Producto productoPrueba;

    private Proveedor proveedorPrueba;

    Faker numeroaleatorio = new Faker(Locale.of("es","CL"));
    @BeforeEach
    public void setUp(){
        Faker faker = new Faker(Locale.of("es","CL"));
        LocalDate fecha = faker.timeAndDate().birthday();
        proveedorPrueba = new Proveedor();
        proveedorPrueba.setNombre(faker.commerce().vendor());
        proveedorPrueba.setId(1L);
        proveedorPrueba.setDireccion(faker.address().fullAddress());
        proveedorPrueba.setTelefono(faker.phoneNumber().phoneNumber());
        proveedorPrueba.setFechaIngreso(fecha);
        proveedorPrueba.setCorreo(faker.internet().emailAddress());


        for (int i=0;i<100;i++){

            Producto producto = new Producto();
            producto.setProductoId((long)i+1);
            producto.setNombreProducto(faker.commerce().productName());
            producto.setPrecioProducto(Double.valueOf(faker.commerce().price().replace(",",".")));
            producto.setDescProducto(faker.commerce().material());
            producto.setProveedorId(proveedorPrueba.getId());
            producto.setCategoriaProducto(faker.commerce().department());
            this.productoList.add(producto);
        }
        this.productoPrueba = this.productoList.get(0);
    }

    @Test
    @DisplayName("debe crear un producto")
    public void shoudlCreateProducto(){
        when(proveedorClientRest.findById(1L)).thenReturn(this.proveedorPrueba);
        when(productoRepository.save(any(Producto.class))).thenReturn(this.productoPrueba);

        Producto result = productoService.save(this.productoPrueba);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.productoPrueba);

        verify(proveedorClientRest, times(1)).findById(1L);
        verify(productoRepository, times(1)).save(any(Producto.class));
    }
    @Test
    @DisplayName("Devuelve todos los productos")
    public void shouldFindAll(){
        when(productoRepository.findAll()).thenReturn(this.productoList);
        List<Producto> result = productoService.findAll();
        assertThat(result).hasSize(100);
        assertThat(result).contains(this.productoPrueba);

        verify(productoRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("encontrar por id un producto")
    public void shouldFindProductoById(){
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoPrueba));
        Producto result = productoService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.productoPrueba);

        verify(productoRepository,times(1)).findById(1L);
    }

    @Test
    @DisplayName("Encontrar por id un producto que no existe")
    public void shouldNotFindProductoById(){
        Long idInexistente = 999L;
        when(productoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> {
            productoService.findById(idInexistente);
        }).isInstanceOf(ProductoException.class)
                .hasMessageContaining("el producto con id: "+idInexistente + " no esta registrado");
        verify(productoRepository,times(1)).findById(idInexistente);
    }
    @Test
    @DisplayName("Debería guardar producto cuando el proveedor existe")
    public void shouldSaveProductoWhenProveedorExists() {
        when(proveedorClientRest.findById(productoPrueba.getProveedorId()))
                .thenReturn(new Proveedor()); // Simular proveedor existente
        when(productoRepository.save(any(Producto.class))).thenReturn(productoPrueba);

        Producto result = productoService.save(productoPrueba);

        assertThat(result).isEqualTo(productoPrueba);
        verify(proveedorClientRest, times(1)).findById(productoPrueba.getProveedorId());
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

}
