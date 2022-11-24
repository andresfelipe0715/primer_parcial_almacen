package com.example.primer_parcial7.services;

import com.example.primer_parcial7.models.Usuario;
import com.example.primer_parcial7.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;
    @InjectMocks
    private Usuario usuario;
    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void listUsuarios() {
        //Given
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("pepe");
        usuario.setApellidos("Rios");
        usuario.setFechaNacimiento(new Date(2004, 7, 14));
        usuario.setCorreo("pepe@gmail.com");
        usuario.setPassword("1234");
        usuario.setDireccion("kdx.010-310");
        usuario.setDocumento("1064836389");
        usuario.setTelefono("3144454761");


        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));


        ResponseEntity<List<Usuario>> usuario1 = usuarioServiceImpl.allUsers();


        Assertions.assertNotNull(usuario1);
    }

    @Test
    void findUsuarioById() {
        //Given
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("andres felipe");
        usuario.setApellidos("guaglianoni");
        usuario.setFechaNacimiento(new Date(2022, 12, 8));
        usuario.setCorreo("falskjfa@gmail.com");
        usuario.setPassword("12343");
        usuario.setDireccion("tabachines");
        usuario.setDocumento("1000000000");
        usuario.setTelefono("3175854157");

        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        ResponseEntity<Usuario> usuario1 = usuarioServiceImpl.getUserById(anyLong());

        Assertions.assertNotNull(usuario1);
    }
    @Test
    void findUsuariosByNombre() {
        //Given
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("andres felipe");
        usuario.setApellidos("guaglianoni");
        usuario.setFechaNacimiento(new Date(2022, 12, 8));
        usuario.setCorreo("falskjfa@gmail.com");
        usuario.setPassword("12343");
        usuario.setDireccion("tabachines");
        usuario.setDocumento("1000000000");
        usuario.setTelefono("3175854157");

        when(usuarioRepository.findAllByNombre("andres felipe")).thenReturn(List.of(usuario));


        ResponseEntity<List<Usuario>> usuario1 = usuarioServiceImpl.allUsersByName("andres felipe");

        Assertions.assertNotNull(usuario1);
    }
    @Test
    void findUsuariosByApellido() {
        //Given
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("andres felipe");
        usuario.setApellidos("guaglianoni");
        usuario.setFechaNacimiento(new Date(2022, 12, 8));
        usuario.setCorreo("falskjfa@gmail.com");
        usuario.setPassword("12343");
        usuario.setDireccion("tabachines");
        usuario.setDocumento("1000000000");
        usuario.setTelefono("3175854157");


        when(usuarioRepository.findAllByApellidos("guaglianoni")).thenReturn(List.of(usuario));


        ResponseEntity<List<Usuario>> usuario1 = usuarioServiceImpl.allUsersByLastName("guaglianoni");


        Assertions.assertNotNull(usuario1);
    }

    @Test
    void usuarioNotFound() {
        Usuario usuario = null;

        when(usuarioRepository.findAll()).thenReturn(Collections.emptyList());

        List<Usuario> usuario1 = usuarioServiceImpl.allUsers().getBody();


        Assertions.assertEquals(null, usuario1);

    }

    @Test
    void UsuarioNotFoundById() {
        Usuario usuario = null;

        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.ofNullable(usuario));

        Usuario usuario1 = usuarioServiceImpl.getUserById(anyLong()).getBody();

        Assertions.assertEquals(null, usuario1);

    }

    @Test
    void usuariosNotFoundByNombre() {
        Usuario usuario = null;

        when(usuarioRepository.findAllByNombre("andres felipe")).thenReturn(Collections.emptyList());

        List<Usuario> usuario1 = usuarioServiceImpl.allUsersByName("andres felipe").getBody();

        Assertions.assertEquals(null, usuario1);

    }


    @Test
    void usuariosNotFoundByApellido() {
        Usuario usuario = null;


        when(usuarioRepository.findAllByApellidos("guaglianoni")).thenReturn(Collections.emptyList());

        List<Usuario> usuario1 = usuarioServiceImpl.allUsersByLastName("guaglianoni").getBody();


        Assertions.assertEquals(null, usuario1);

    }


}