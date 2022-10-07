package com.example.primer_parcial7.repository;

import com.example.primer_parcial7.models.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticuloRepository extends JpaRepository <Articulo, Long >{
    Optional<Articulo> findByCodigo(String codigo);


}




