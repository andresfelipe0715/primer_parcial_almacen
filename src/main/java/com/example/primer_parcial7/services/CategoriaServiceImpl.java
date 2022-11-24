package com.example.primer_parcial7.services;

import com.example.primer_parcial7.models.Categoria;
import com.example.primer_parcial7.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public ResponseEntity<Categoria> createCategory(Categoria categoria) {
        try {
            categoriaRepository.save(categoria);
            return  new ResponseEntity(categoria, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<Categoria>> allCategory() {
        List<Categoria> categorias = categoriaRepository.findAll();
        if (categorias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(categorias, HttpStatus.OK);

    }
}