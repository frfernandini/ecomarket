package com.ecomarket.msvc_proveedores.init;

import com.ecomarket.msvc_proveedores.models.Proveedor;
import com.ecomarket.msvc_proveedores.repositories.ProveedorRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import net.datafaker.Faker;
import java.time.temporal.ChronoUnit;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Profile("dev")
@Component
public class LoadDatabase implements CommandLineRunner {
    @Autowired
    private ProveedorRepository proveedorRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);
    @Override
    public void run (String... args)throws Exception{
        Faker faker = new Faker(Locale.of("es","CL"));
        Set<String> nombreUsados = new HashSet<>();
        if (proveedorRepository.count() == 0) {
            for(int i=0;i<100;i++){
                String nombre;
                do {
                    nombre = faker.name().firstName();
                } while (nombreUsados.contains(nombre));
                nombreUsados.add(nombre);
                Proveedor proveedor = new Proveedor();
                LocalDate fecha = faker.timeAndDate().birthday();
                proveedor.setNombre(nombre);
                proveedor.setDireccion(faker.address().fullAddress());
                proveedor.setTelefono(faker.phoneNumber().phoneNumber());
                proveedor.setFechaIngreso(fecha);
                proveedor.setCorreo(faker.internet().emailAddress());
                logger.info("El proveedor que agregas es {}", proveedor.getNombre());
                proveedorRepository.save(proveedor);
                logger.info("el proveedor creado es: {}", proveedor);
            }
        }
    }
}
