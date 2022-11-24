package com.example.primer_parcial7.controller;

import com.example.primer_parcial7.models.Usuario;
import com.example.primer_parcial7.services.UsuarioService;
import com.example.primer_parcial7.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JWTUtil jwtUtil;
    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id, @RequestHeader(value="Authorization") String token){

        try{
            if(jwtUtil.getKey(token) != null) {
                return usuarioService.getUserById(id);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }

    }
    @PostMapping("/usuario")
    public ResponseEntity crearUsuario(@Valid @RequestBody Usuario usuario){

        return usuarioService.createUser(usuario);
    }
    @GetMapping(value="/usuarios")
    public ResponseEntity listarUsuarios(@RequestHeader(value="Authorization") String token){

        try{
            if(jwtUtil.getKey(token) != null) {
                return usuarioService.allUsers();
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }

    }

    @GetMapping(value="/usuarios/nombre/{nombre}")
    public ResponseEntity listarPorNombre(@PathVariable  String nombre , @RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                return usuarioService.allUsersByName(nombre);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }

    }
    @GetMapping(value="/usuarios/apellidos/{apellidos}")
    public ResponseEntity listarPorapellidos(@PathVariable  String apellidos , @RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                return usuarioService.allUsersByLastName(apellidos);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }

    }
    @PutMapping(value = "/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id,@RequestBody @Valid Usuario usuario, @RequestHeader(value="Authorization") String token){

        try{
            if(jwtUtil.getKey(token) != null) {
                return usuarioService.editUser(id,usuario);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }

    }
    @DeleteMapping (value = "/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id, @RequestHeader(value="Authorization") String token) {

        try{
            if(jwtUtil.getKey(token) != null) {
                return usuarioService.deleteUser(id);
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