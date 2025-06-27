package com.ecomarket.msvc_proveedores.services;


import com.ecomarket.msvc_proveedores.exceptions.ProveedorException;
import com.ecomarket.msvc_proveedores.models.Proveedor;
import com.ecomarket.msvc_proveedores.repositories.ProveedorRepository;
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
public class ProveedorServiceTest {
    @Mock
    private ProveedorRepository proveedorRepository;


    @InjectMocks
    private ProveedorServiceImpl proveedorService;

    private List<Proveedor> proveedorList = new ArrayList<>();

    private Proveedor proveedorprueba;

    @BeforeEach
    public void setUp() {
        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i=0;i<100;i++){
            Proveedor proveedor = new Proveedor();
            LocalDate fecha = faker.timeAndDate().birthday();
            proveedor.setNombre(faker.commerce().vendor());
            proveedor.setProveedoId((long) i+1);
            proveedor.setDireccion(faker.address().fullAddress());
            proveedor.setTelefono(faker.phoneNumber().phoneNumber());
            proveedor.setFechaIngreso(fecha);
            proveedor.setCorreo(faker.internet().emailAddress());
            this.proveedorList.add(proveedor);
        }
        this.proveedorprueba = this.proveedorList.get(0);
    }

    @Test
    @DisplayName("devuelve todos los proveedores")
    public void shouldFindAllProveedores() {
        when(proveedorRepository.findAll()).thenReturn(this.proveedorList);
        List<Proveedor> result = proveedorService.findAll();
        assertThat(result).hasSize(100);
        assertThat(result).contains(this.proveedorprueba);

        verify(proveedorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Encontrar por id un proveedor")
    public void shouldFindProveedorById() {
        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(proveedorprueba));
        Proveedor result = proveedorService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.proveedorprueba);

        verify(proveedorRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Encontrar por id un proveedor que no existe")
    public void shouldNotFindProveedorById() {
        Long idInexistente = 999L;
        when(proveedorRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            proveedorService.findById(idInexistente);
        }).isInstanceOf(ProveedorException.class)
                .hasMessage("Proveedor con id: "+idInexistente +" no encontrado");
        verify(proveedorRepository, times(1)).findById(idInexistente);

    }
    @Test
    @DisplayName("Deberia guardar un proveedor")
    public void shouldSaveProveedor() {
        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(this.proveedorprueba);
        Proveedor result = proveedorService.save(this.proveedorprueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.proveedorprueba);
        verify(proveedorRepository, times(1)).save(any(Proveedor.class));
    }
    @Test
    @DisplayName("guardar un proveedor ya existente")
    public void shouldThrowExceptionWhenSavingExistingProveedor() {
        when(proveedorRepository.findByNombre(proveedorprueba.getNombre()))
                .thenReturn(Optional.of(proveedorprueba));

        assertThatThrownBy(() -> proveedorService.save(proveedorprueba))
                .isInstanceOf(ProveedorException.class)
                .hasMessage("Proveedor ya existe");

        verify(proveedorRepository, times(1)).findByNombre(proveedorprueba.getNombre());
        verify(proveedorRepository, never()).save(any());
    }
    @Test
    @DisplayName("Debería eliminar proveedor por ID")
    public void shouldDeleteProveedorById() {
        doNothing().when(proveedorRepository).deleteById(1L);

        proveedorService.deleteById(1L);

        verify(proveedorRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debería actualizar proveedor existente")
    public void shouldUpdateProveedor() {
        Proveedor proveedorActualizado = new Proveedor();
        proveedorActualizado.setNombre("Nuevo Nombre");
        proveedorActualizado.setCorreo("nuevo@correo.com");

        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(proveedorprueba));
        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(proveedorActualizado);

        Proveedor result = proveedorService.update(1L, proveedorActualizado);

        assertThat(result.getNombre()).isEqualTo("Nuevo Nombre");
        assertThat(result.getCorreo()).isEqualTo("nuevo@correo.com");
        verify(proveedorRepository, times(1)).findById(1L);
        verify(proveedorRepository, times(1)).save(any(Proveedor.class));
    }

    @Test
    @DisplayName("actualizar proveedor inexistente")
    public void shouldThrowExceptionWhenUpdatingNonExistingProveedor() {
        Long idInexistente = 999L;
        Proveedor proveedorActualizado = new Proveedor();

        when(proveedorRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> proveedorService.update(idInexistente, proveedorActualizado))
                .isInstanceOf(ProveedorException.class)
                .hasMessage("Proveedor no encontrado");

        verify(proveedorRepository, times(1)).findById(idInexistente);
        verify(proveedorRepository, never()).save(any());
    }
}
