package com.jcandia.msvc.ventas.msvc_ventas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcVentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcVentasApplication.class, args);
	}

}

