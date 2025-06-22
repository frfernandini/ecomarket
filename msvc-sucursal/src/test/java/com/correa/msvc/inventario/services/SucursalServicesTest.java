package com.correa.msvc.inventario.services;


import com.correa.msvc.inventario.exceptions.SucursalException;
import com.correa.msvc.inventario.models.Sucursal;
import com.correa.msvc.inventario.repositories.SucursalRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SucursalServicesTest {

    @Mock
    private SucursalRepository sucursalRepository;

    @InjectMocks
    private SucursalServiceImpl sucursalService;

    private List<Sucursal> sucursalList = new ArrayList<>();

    private Sucursal sucursalPrueba;

    @BeforeEach
    public void setUp() {
        Faker faker = new Faker(Locale.of("es", "CL"));
        for (int i = 0; i < 100; i++) {

            Sucursal sucursal = new Sucursal();

            sucursal.setTienda(faker.commerce().department() + " " + faker.address().cityName());
            sucursal.setTelefono("+56 9" + faker.number().numberBetween(1000, 9999) + " " + faker.number().numberBetween(1000, 9999));
            sucursal.setHorarioApertura(faker.number().numberBetween(8, 10) + ":00");
            sucursal.setHorarioCerrar(faker.number().numberBetween(20, 22) + ":00");
            this.sucursalList.add(sucursal);
        }
        this.sucursalPrueba = this.sucursalList.get(0);
    }


    @Test
    @DisplayName("devuelve todas las sucursales")
    public void shouldFindAll(){
        when(sucursalRepository.findAll()).thenReturn(this.sucursalList);
        List<Sucursal> result = this.sucursalService.findAll();
        assertThat(result).hasSize(100);
        assertThat(result).contains(this.sucursalPrueba);

        verify(sucursalRepository, times(1)).findAll();
    }
    @Test
    @DisplayName("Encontrar una Sucursal por ID")
    public void shouldFindAllSucursal() {
        when(sucursalRepository.findById(1L)).thenReturn(Optional.of(sucursalPrueba));
        Sucursal result = sucursalService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.sucursalPrueba);

        verify(sucursalRepository,times(1)).findById(1L);
    }

    @Test
    @DisplayName("Encontrar por id una Sucursal que no existe")
    public void shouldSaveSucursal() {
        Long idInexistente= 1L;
        when(sucursalRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            sucursalService.findById(idInexistente);
        }).isInstanceOf(SucursalException.class)
                .hasMessageContaining("No se encontro el sucursal con id: " + idInexistente);
        verify(sucursalRepository,times(1)).findById(idInexistente);

    }
    @Test
    @DisplayName("Deberia Guardar una Sucursal")
    public void ShouldSave(){
        when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursalPrueba);
        Sucursal result = sucursalService.save(sucursalPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.sucursalPrueba);
        verify(sucursalRepository,times(1)).save(any(Sucursal.class));

    }
}

