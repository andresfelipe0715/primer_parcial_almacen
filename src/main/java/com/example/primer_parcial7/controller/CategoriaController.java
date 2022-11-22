package com.example.primer_parcial7.controller;

import com.example.primer_parcial7.models.Categoria;
import com.example.primer_parcial7.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class CategoriaController{
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping(value = "/categoria/{id}")
    public ResponseEntity getCategoria(@PathVariable Long id){
        Optional<Categoria> categoria= categoriaRepository.findById(id);
        if(categoria.isPresent()){
            return new ResponseEntity(categoria, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/categoria")
    public ResponseEntity crearCategoria (@RequestBody Categoria categoria){
        try{
            categoriaRepository.save(categoria);
            return new ResponseEntity(categoria, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/categorias")
    public ResponseEntity listarCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();
        if (categorias.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(categorias,HttpStatus.OK);
    }
}
