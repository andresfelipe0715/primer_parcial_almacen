package com.example.primer_parcial7.controller;

import com.example.primer_parcial7.models.Articulo;
import com.example.primer_parcial7.models.Category;
import com.example.primer_parcial7.repository.ArticuloRepository;
import com.example.primer_parcial7.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(value = "/category/{id}")
    public ResponseEntity getCategory(@PathVariable Long id){
        Optional<Category> category= categoryRepository.findById(id);
        if(category.isPresent()){
            return new ResponseEntity(category, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();

    }


    @GetMapping(value = "/categories")
    public ResponseEntity listarCategories(){
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(categories,HttpStatus.OK);
    }

    @PostMapping(value = "/category")
    public ResponseEntity crearCategory(@RequestBody Category category){
        try {
            categoryRepository.save(category);
            return new ResponseEntity(category,HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
