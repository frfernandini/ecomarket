package com.jcandia.msvc.ventas.msvc_ventas.init;


import com.jcandia.msvc.ventas.msvc_ventas.models.entities.Ventas;
import com.jcandia.msvc.ventas.msvc_ventas.repository.VentaRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.time.*;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Profile("dev")
@Component
public class LoadDatabase implements CommandLineRunner {


    @Autowired
    private VentaRepository ventaRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es","CL"));

        if(ventaRepository.count() == 0) {
            for(int i=0;i<100;i++){

                LocalDate fechadate = faker.timeAndDate().birthday();

                LocalDateTime fechatime = fechadate.atStartOfDay();

                Ventas venta = new Ventas();
                venta.setCantidadProductoVenta(faker.number().randomDigit());
                venta.setFechaHoraVenta(fechatime);
                venta.setIdProducto(faker.number().randomNumber());
                venta.setIdSucursal(faker.number().randomNumber());
                venta.setIdUsuario(faker.number().randomNumber());
                logger.info("la venta que agregas es {}", venta.getIdVenta());
                ventaRepository.save(venta);
                logger.info("la venta creada es {}", venta);
            }
        }
    }
}
