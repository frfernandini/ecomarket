package com.correa.msvc.inventario.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("sucursal")
                        .description("esta es la seccion donde se encuetras todos " + "los endpoints de MSVC sucursal")
                        .version("1.0.0")
                );
    }
}
