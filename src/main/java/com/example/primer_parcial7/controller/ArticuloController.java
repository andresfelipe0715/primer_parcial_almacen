package com.example.primer_parcial7.controller;


import com.example.primer_parcial7.models.Articulo;
import com.example.primer_parcial7.repository.ArticuloRepository;
import com.example.primer_parcial7.repository.CategoriaRepository;
import com.example.primer_parcial7.services.ArticuloService;
import com.example.primer_parcial7.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class ArticuloController {

    @Autowired
    private ArticuloRepository articuloRepository;
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ArticuloService articuloService;
    @Autowired
    private JWTUtil jwtUtil;
    @GetMapping("/articulos")
    public ResponseEntity listarArticulos(@RequestHeader(value="Authorization") String token) {

        try{
            if(jwtUtil.getKey(token) != null) {
                return articuloService.allArticles();
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }


    }

    @GetMapping("/articulo/codigo/{codigo}")
    public ResponseEntity getArticulo(@PathVariable String codigo,@RequestHeader(value="Authorization") String token) {
        try{
            if(jwtUtil.getKey(token) != null) {
                return articuloService.getArticleFindBycodigo(codigo);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }



    }
    @PostMapping("/articulo")
    public ResponseEntity crearArticulo(@RequestBody Articulo articulo,@RequestHeader(value="Authorization") String token){

        try{
            if(jwtUtil.getKey(token) != null) {
                return articuloService.createArticle(articulo);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }

    }
    @DeleteMapping("/articulo/codigo/{codigo}")
    public ResponseEntity eliminarArticulo(@PathVariable String codigo,@RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                return articuloService.deleteArticle(codigo);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }


    }
    @PutMapping("/articulo/{codigo}")
    public ResponseEntity editarArticulo(@PathVariable String codigo,@RequestBody Articulo articulo,@RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                return articuloService.editArticle(codigo,articulo);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }



    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
