package com.correa.msvc.inventario.init;

import com.correa.msvc.inventario.models.Sucursal;
import com.correa.msvc.inventario.repositories.SucursalRepository;
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


}
