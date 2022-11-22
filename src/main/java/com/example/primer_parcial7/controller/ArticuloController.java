package com.example.primer_parcial7.controller;


import com.example.primer_parcial7.models.Articulo;
import com.example.primer_parcial7.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArticuloController {
    @Autowired
    private ArticuloRepository articuloRepository;

    @GetMapping(value = "/articulo/{codigo}")
    public ResponseEntity getArticulo(@PathVariable String codigo){
        Optional<Articulo> articulo= articuloRepository.findByCodigo(codigo);
        if(articulo.isPresent()){
            return new ResponseEntity(articulo, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/articulo")
    public ResponseEntity crearArticulo (@RequestBody Articulo articulo){
        try{
            articuloRepository.save(articulo);
            return new ResponseEntity(articulo, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/articulos")
    public ResponseEntity listarArticulos(){
        List<Articulo> articulos = articuloRepository.findAll();
        if (articulos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(articulos,HttpStatus.OK);
    }

    @PutMapping("/articulo/{codigo}")
    public ResponseEntity editarArticulo(@PathVariable String codigo, @RequestBody Articulo articulo){
        Optional<Articulo> articuloBD = articuloRepository.findByCodigo(codigo);
        if (articuloBD.isPresent()){
            try {
                articuloBD.get().setCodigo(articulo.getCodigo());
                articuloBD.get().setNombre(articulo.getNombre());
                articuloBD.get().setDescripcion(articulo.getDescripcion());
                articuloBD.get().setFechaRegistro(articulo.getFechaRegistro());
                articuloBD.get().setCategoria(articulo.getCategoria());
                articuloBD.get().setStock(articulo.getStock());
                articuloBD.get().setPrecioVenta(articulo.getPrecioVenta());
                articuloBD.get().setPrecioCompra(articulo.getPrecioCompra());
                articuloRepository.save(articuloBD.get());
                return new ResponseEntity(articuloBD,HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }

        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/articulo/{codigo}")
    public ResponseEntity eliminarArticulo(@PathVariable String codigo){
        Optional<Articulo> articuloBD = articuloRepository.findByCodigo(codigo);
        if (articuloBD.isPresent()){
            articuloRepository.delete(articuloBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}