package com.correa.msvc.inventario.services;

import com.correa.msvc.inventario.clients.ProductoClientRest;
import com.correa.msvc.inventario.clients.SucursalClientRest;
import com.correa.msvc.inventario.exceptions.InventarioException;
import com.correa.msvc.inventario.models.entities.Inventario;
import com.correa.msvc.inventario.repositories.InventarioRepository;
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

    @BeforeEach
    public void setUp(){
        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i=0;i<100;i++){

            Inventario inventario = new Inventario();
            LocalDate fecha = faker.timeAndDate().birthday();
            inventario.setInventarioId((long)i+1);
            inventario.setCantidadInventario(faker.number().numberBetween(1,1000));
            inventario.setFechaIngresoProducto(fecha);
            inventario.setIdProducto(faker.number().numberBetween(1L,10L));
            inventario.setIdSucursal(faker.number().numberBetween(1L,10L));
            this.inventarioList.add(inventario);
        }
        this.inventarioPrueba= this.inventarioList.get(0);
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
}
