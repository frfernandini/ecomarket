package com.cjohao.msvc.usuarios.msvc_usuarios.services;


import com.cjohao.msvc.usuarios.msvc_usuarios.exceptions.UsuarioExceptions;
import com.cjohao.msvc.usuarios.msvc_usuarios.models.Usuarios;
import com.cjohao.msvc.usuarios.msvc_usuarios.repositories.UsuarioRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServicesTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServicesImpl usuarioServices;

    private List<Usuarios> usuariosList = new ArrayList<>();

    private Usuarios usuariosPrueba;


    @BeforeEach
    public void setUp(){
        Faker faker = new Faker(Locale.of("es","CL"));
        if(usuarioRepository.count()==0){
            for(int i=0;i<100;i++){

                Usuarios usuarios = new Usuarios();

                usuarios.setIdUsuario((long)i+1);
                usuarios.setNombresUsuario(faker.name().firstName());
                usuarios.setApellidosUsuario(faker.name().lastName());
                usuarios.setCorreoUsuario(faker.internet().emailAddress());
                usuarios.setContraseña(faker.internet().password());

                LocalDate fechaRegistro = faker.timeAndDate().birthday();
                usuarios.setRegistroUsuario(fechaRegistro);

                String numeroString = faker.idNumber().valid().replaceAll("-","");
                String ultimo = numeroString.substring(numeroString.length()-1);
                String restante = numeroString.substring(0,numeroString.length()-1);

                usuarios.setRun(restante+"-"+ultimo);
                this.usuariosList.add(usuarios);

            }
            this.usuariosPrueba = this.usuariosList.get(0);
        }
    }

    @Test
    @DisplayName("Devuelve todos los Usuarios")
    public void shouldFindAllUsuarios(){
        when(usuarioRepository.findAll()).thenReturn(this.usuariosList);
        List<Usuarios> result = usuarioServices.findAll();
        assertThat(result).hasSize(100);
        assertThat(result).contains(this.usuariosPrueba);

        verify(usuarioRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Encontrar por id un Usuario")
    public void shouldFindUsuarioById() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuariosPrueba));
        Usuarios result = usuarioServices.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.usuariosPrueba);

        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Encontrar por id un Usuario que no existe")
    public void shouldNotFindUsuarioById() {
        Long idInexistente = 1L;
        when(usuarioRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            usuarioServices.findById(idInexistente);
        }).isInstanceOf(UsuarioExceptions.class)
                .hasMessage("El usuario con id "+idInexistente+" no existe");
        verify(usuarioRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Deberia guardar un Usuario")
    public void shouldSaveUsuarios() {
        when(usuarioRepository.save(any(Usuarios.class))).thenReturn(this.usuariosPrueba);
        Usuarios result = usuarioServices.save(this.usuariosPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.usuariosPrueba);
        verify(usuarioRepository, times(1)).save(any(Usuarios.class));
    }
}
