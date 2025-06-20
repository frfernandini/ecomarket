package com.cjohao.msvc.usuarios.msvc_usuarios.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Msvc Usuarios")
                        .description("esta es la seccion onde  se encutran todos los endpoints de msvc usuarios")
                        .version("1.0.0")
                );
    }
}
