package com.example.primer_parcial7.repository;

import com.example.primer_parcial7.models.Articulo;
import com.example.primer_parcial7.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long > {

    Optional<Category> findById(Long id);
}
