package com.example.primer_parcial7.services;

import com.example.primer_parcial7.models.Articulo;
import com.example.primer_parcial7.repository.ArticuloRepository;
import com.example.primer_parcial7.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServiceImpl implements ArticuloService {
    @Autowired
    private ArticuloRepository articuloRepository;
    private CategoriaRepository categoriaRepository;
    @Override
    public ResponseEntity<List<Articulo>> allArticles() {
        List<Articulo> articulos = articuloRepository.findAll();
        if (articulos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(articulos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Articulo> getArticleFindBycodigo(String codigo) {
        Optional articulo = articuloRepository.findByCodigo(codigo);
        if (articulo.isPresent()) {
            return new ResponseEntity(articulo, HttpStatus.OK);

        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Articulo> createArticle(Articulo articulo) {

        try {
            articuloRepository.save(articulo);
            return new ResponseEntity(articulo, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    public ResponseEntity<Articulo> deleteArticle(String codigo) {
        Optional<Articulo> articuloBD = articuloRepository.findByCodigo(codigo);
        if(articuloBD.isPresent()){
            articuloRepository.delete(articuloBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Articulo> editArticle(String codigo, Articulo articulo) {
        Optional<Articulo> articuloBD =articuloRepository.findByCodigo(codigo);
        if (articuloBD.isPresent()) {
            try {
                articuloBD.get().setCodigo(articulo.getCodigo());
                articuloBD.get().setCategoria(articulo.getCategoria());
                articuloBD.get().setNombre(articulo.getNombre());
                articuloBD.get().setFechaRegistro(articulo.getFechaRegistro());
                articuloBD.get().setStock(articulo.getStock());
                articuloBD.get().setPrecioVenta(articulo.getPrecioVenta());
                articuloBD.get().setPrecioCompra(articulo.getPrecioCompra());
                articuloBD.get().setDescripcion(articulo.getDescripcion());
                articuloRepository.save(articuloBD.get());
                return new ResponseEntity(articuloBD, HttpStatus.OK);

            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }


        }
        return ResponseEntity.notFound().build();

    }
}
