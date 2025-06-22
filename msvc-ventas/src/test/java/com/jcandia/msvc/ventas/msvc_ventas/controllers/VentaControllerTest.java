package com.jcandia.msvc.ventas.msvc_ventas.controllers;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VentaControllerTest {

    @Autowired
    TestRestTemplate restTemplate;


    @Test
    @DisplayName("Debe entregar un listado con todos las ventas")
    public void shouldReturnAllVentasWhenListIsRequired(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/ventas", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int ventasCount = documentContext.read("$.length()");
        assertThat(ventasCount).isGreaterThan(1000);
    }
}
