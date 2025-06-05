package com.fernandini.msvc.productos.controllers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class productoControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Debe entregar un listado con todos los productos")
    public void shouldReturnAllProductoWhenListIsRequired(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("api/v1/productos",String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int productoCount = documentContext.read("$.lenght()");
        assertThat(productoCount).isEqualTo(1000);
    }
}
