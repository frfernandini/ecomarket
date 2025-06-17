package com.cjohao.msvc.usuarios.msvc_usuarios.init;

import com.cjohao.msvc.usuarios.msvc_usuarios.models.Usuarios;
import com.cjohao.msvc.usuarios.msvc_usuarios.repositories.UsuarioRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;

@Profile("test")
@Component
public class LoadDataBase implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDataBase.class);

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es","CL"));

        if(usuarioRepository.count()==0){
            for(int i=0;i<1000;i++){
                Usuarios usuarios = new Usuarios();

                usuarios.setNombresUsuario(faker.name().firstName());
                usuarios.setApellidosUsuario(faker.name().lastName());
                usuarios.setCorreoUsuario(faker.internet().emailAddress());
                usuarios.setContraseña(faker.internet().password());
                usuarios.setRegistroUsuario(LocalDate.now());

                String numeroString = faker.idNumber().valid().replaceAll("-","");
                String ultimo = numeroString.substring(numeroString.length()-1);
                String restante = numeroString.substring(0,numeroString.length()-1);

                usuarios.setRun(restante+"-"+ultimo);
                logger.info("El run que agregas es {}", usuarios.getRun());
                usuarios = usuarioRepository.save(usuarios);
                logger.info("El usuario creado es {}", usuarios);

            }
        }


    }
}
