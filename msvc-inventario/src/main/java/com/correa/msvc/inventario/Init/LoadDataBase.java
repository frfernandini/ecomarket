package com.correa.msvc.inventario.Init;


import com.correa.msvc.inventario.models.entities.Inventario;
import com.correa.msvc.inventario.repositories.InventarioRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;

@Profile("dev")
@Component
public class LoadDataBase implements CommandLineRunner {

    @Autowired
    private InventarioRepository inventarioRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDataBase.class);

    @Override
    public void run (String... args)throws Exception{
        Faker faker = new Faker(Locale.of("es","CL"));
        if(inventarioRepository.count()==0){
            for(int i =0; i <1000; i ++){
                Inventario inventario = new Inventario();

                LocalDate fecha = faker.timeAndDate().birthday();


                inventario.setCantidadInventario(faker.number().numberBetween(1,1000));
                inventario.setFechaIngresoProducto(fecha);
                inventario.setIdProducto(faker.number().numberBetween(1L,10L));
                inventario.setIdSucursal(faker.number().numberBetween(1L,10L));

                logger.info("Agregar a Inventraio"+inventario);
                inventario = inventarioRepository.save(inventario);
                logger.info("La fecha es: "+ inventario.getFechaIngresoProducto());
                logger.info("La cantidad es: "+ inventario.getCantidadInventario());
                logger.info("El id del producto es: "+inventario.getIdProducto());
                logger.info("El id de sucursal es: "+inventario.getIdSucursal());

            }
        }
    }


}
