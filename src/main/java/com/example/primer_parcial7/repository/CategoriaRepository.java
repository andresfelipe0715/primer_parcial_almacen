package com.example.primer_parcial7.repository;

import com.example.primer_parcial7.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    //List<Categoria> findAllByNombre(String nombre);

}