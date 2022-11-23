package com.example.primer_parcial7.controller;
import com.example.primer_parcial7.models.Categoria;
import com.example.primer_parcial7.services.CategoriaService;
import com.example.primer_parcial7.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private JWTUtil jwtUtil;
    @GetMapping("/categorias")
    public ResponseEntity listarCategorias(@RequestHeader(value="Authorization") String token) {
        try{
            if(jwtUtil.getKey(token) != null) {
                return categoriaService.allCategory();
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }

    }


    @PostMapping("/categoria")
    public ResponseEntity crearCategoria(@RequestBody Categoria categoria, @RequestHeader(value="Authorization") String token){

        try{
            if(jwtUtil.getKey(token) != null) {
                return categoriaService.createCategory(categoria);
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