package com.fernandini.msvc.productos.init;


import com.fernandini.msvc.productos.models.Producto;
import com.fernandini.msvc.productos.repositories.ProductoRepository;
import net.datafaker.Faker;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Profile("dev")
@Component
public class LoadDatabase implements CommandLineRunner {
    @Autowired
    private ProductoRepository productoRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es","CL"));
        Set<String> nombreUsados = new HashSet<>();

        if(productoRepository.count()==0){

            for(int i =0;i<100;i++){
                Producto producto = new Producto();
                String nombre;
                do {
                    nombre = faker.commerce().productName();
                } while (nombreUsados.contains(nombre));
                producto.setNombreProducto(faker.commerce().productName());
                producto.setPrecioProducto(Double.valueOf(faker.commerce().price().replace(",",".")));
                producto.setDescProducto(faker.commerce().material());
                producto.setProveedorId(faker.number().numberBetween(1L,100L));
                producto.setCategoriaProducto(faker.commerce().department());
                logger.info("el producto que agregas es "+ producto.getNombreProducto());
                producto = productoRepository.save(producto);
                logger.info("el product creado es"+producto);
            }
        }
    }
}
