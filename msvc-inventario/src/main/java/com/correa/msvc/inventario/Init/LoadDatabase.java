package com.correa.msvc.inventario.Init;

import com.correa.msvc.inventario.models.entities.Inventario;
import com.correa.msvc.inventario.repositories.InventarioRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import net.datafaker.Faker;

import java.util.Locale;

@Profile("dev")
@Component
public class LoadDatabase {

    @Autowired
    private InventarioRepository inventarioRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);


    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es", "CL"));

        if(inventarioRepository.count()==0){
            for(int i =0;i<1000;i++){
                Inventario inventario = new Inventario();
                inventario.setCantidadInventario(faker.number().digit(5));
                producto.setPrecioProducto(Double.valueOf(faker.commerce().price()));
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
