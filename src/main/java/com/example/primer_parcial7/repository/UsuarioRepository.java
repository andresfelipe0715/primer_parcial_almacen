package com.example.primer_parcial7.repository;

import com.example.primer_parcial7.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository <Usuario,Long> {
    List<Usuario> findAllByNombre(String nombre);

    List<Usuario> findAllByApellidos(String apellidos);

    Usuario findByCorreo(String correo);

}
