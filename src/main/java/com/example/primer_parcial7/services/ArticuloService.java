package com.example.primer_parcial7.services;

import com.example.primer_parcial7.models.Articulo;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface ArticuloService {
    ResponseEntity <List<Articulo>> allArticles();
    ResponseEntity <Articulo> getArticleFindBycodigo(String codigo);
    ResponseEntity <Articulo> createArticle( Articulo articulo);
    ResponseEntity <Articulo> deleteArticle(String codigo);
    ResponseEntity <Articulo> editArticle( String codigo, Articulo articulo);

}
