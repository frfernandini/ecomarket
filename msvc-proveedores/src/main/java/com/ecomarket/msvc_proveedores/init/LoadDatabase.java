package com.ecomarket.msvc_proveedores.init;

import com.ecomarket.msvc_proveedores.models.Proveedor;
import com.ecomarket.msvc_proveedores.repositories.ProveedorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

@Profile("dev")
@Component
public class LoadDatabase {
    @Autowired
    private ProveedorRepository proveedorRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Override
    public void run(String... args) throws Exception{
        Faker faker = new Faker(Locale.of("es","CL"));

        if(proveedorRepository.count() == 0){
            for(int i = 0; i<100; i++){
                Proveedor proveedor = new Proveedor();
                String fechaFormateada = faker.date().birthday().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                proveedor.setNombre(faker.name().name());
                proveedor.setProveedoId(faker.number().numberBetween(1L,100L));
                proveedor.setDireccion(faker.address().fullAddress());
                proveedor.setTelefono(faker.phoneNumber().phoneNumber());
                proveedor.setFechaIngreso(faker.date());
        }
    }
}
