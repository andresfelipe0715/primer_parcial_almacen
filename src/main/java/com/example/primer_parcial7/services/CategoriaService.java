package com.example.primer_parcial7.services;

import com.example.primer_parcial7.models.Categoria;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoriaService {
    ResponseEntity<Categoria> createCategory(Categoria categoria);
    ResponseEntity<List<Categoria>>allCategory();
}
