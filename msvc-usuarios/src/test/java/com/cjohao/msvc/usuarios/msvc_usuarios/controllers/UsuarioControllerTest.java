package com.cjohao.msvc.usuarios.msvc_usuarios.controllers;

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
public class UsuarioControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Debe entregar un listado con todos los usuarios")
    public void shouldReturnAllUsuarioWhenListRequired(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/v1/usuarios", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int usuarioCount = documentContext.read("$.length()");
        assertThat(usuarioCount).isEqualTo(1000);
    }
}
