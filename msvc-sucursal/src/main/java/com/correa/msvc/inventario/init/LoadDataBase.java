package com.correa.msvc.inventario.init;

import com.correa.msvc.inventario.models.Sucursal;
import com.correa.msvc.inventario.repositories.SucursalRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import java.util.Locale;

@Profile("dev")
@Component
public class LoadDataBase implements CommandLineRunner {

    @Autowired
    private SucursalRepository sucursalRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDataBase.class);

    @Override
    public void run (String... args)throws Exception {
        Faker faker = new Faker(Locale.of("es", "CL"));
        if (sucursalRepository.count() == 0) {
            for (int i = 0; i < 1000; i++) {
                Sucursal sucursal = new Sucursal();


                sucursal.setTienda(faker.commerce().department() + " " + faker.address().cityName());
                sucursal.setTelefono("+56 9" + faker.number().numberBetween(1000, 9999) + " " + faker.number().numberBetween(1000, 9999));
                sucursal.setHorarioApertura(faker.number().numberBetween(8, 10) + ":00");
                sucursal.setHorarioCerrar(faker.number().numberBetween(20, 22) + ":00");

                logger.info("Agregando Sucursal: " + sucursal);
                sucursal = sucursalRepository.save(sucursal);

                logger.info("Nombre Tienda: " + sucursal.getTienda());
                logger.info("Direccion:" + sucursal.getTelefono());
                logger.info("Telefono: " + sucursal.getTelefono());
                logger.info("Horario Apertura: " + sucursal.getHorarioApertura());
                logger.info("Horario Cerrar: " + sucursal.getHorarioCerrar());
            }
        }
    }

}
