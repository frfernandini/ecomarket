package com.correa.msvc.inventario.controllers;

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
public class InventarioControllersTest {

    @Autowired
    TestRestTemplate restTemplate;


    @Test
    @DisplayName("Debe entregar un listado de todos los Inventarios")
    public void shouldReturnAllInventariosWhenListIsRequired(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/inventarios", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int inventarioCount = documentContext.read("$.length()");
        assertThat(inventarioCount).isEqualTo(1000);

    }


}
