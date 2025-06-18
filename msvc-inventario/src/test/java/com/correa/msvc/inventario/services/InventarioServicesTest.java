package com.correa.msvc.inventario.services;

import com.correa.msvc.inventario.models.entities.Inventario;
import com.correa.msvc.inventario.repositories.InventarioRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ExtendWith(MockitoExtension.class)
public class InventarioServicesTest {

    @Mock
    private InventarioRepository inventarioRepository;

    private List<Inventario> inventarioList = new ArrayList<>();

    private Inventario inventarioPrueba;

    @BeforeEach
    public void setUp(){
        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i=0;i<100;i++){

            Inventario inventario = new Inventario();
            LocalDate fecha = faker.timeAndDate().birthday();

            inventario.setCantidadInventario(faker.number().numberBetween(1,1000));
            inventario.setFechaIngresoProducto(fecha);
            inventario.setIdProducto(faker.number().numberBetween(1L,10L));
            inventario.setIdSucursal(faker.number().numberBetween(1L,10L));
            this.inventarioList.add(inventario);
        }
        this.inventarioPrueba= this.inventarioList.get(0);
    }
    @Test
    @DisplayName("Encontrar un inventraio por ID")
    public void shouldFindAllProveedores(){

    }
}
