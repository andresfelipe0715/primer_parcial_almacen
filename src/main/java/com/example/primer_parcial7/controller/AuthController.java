package com.example.primer_parcial7.controller;

import com.example.primer_parcial7.models.Usuario;
import com.example.primer_parcial7.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/auth/login")
    public ResponseEntity login(@RequestBody Usuario usuario){
        return  usuarioService.login(usuario.getCorreo(),usuario.getPassword());
    }
}
