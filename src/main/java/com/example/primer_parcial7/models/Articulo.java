package com.example.primer_parcial7.models;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "articulos")

public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false, unique = true)
    private String codigo;
    @Column(length = 100, nullable = false)
    private String nombre;
    @Column(length = 300, nullable = false)
    private String descripcion;
    private Date fechaRegistro;
    @ManyToOne
    private Categoria categoria;
    @Column(nullable = false)
    private int stock;
    @Column(nullable = false, scale = 2)
    private double precioVenta;
    @Column(nullable = false, scale = 2)
    private double precioCompra;
}

