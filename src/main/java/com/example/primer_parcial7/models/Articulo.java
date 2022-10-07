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
    private String codigo;
    @Column(length = 100,nullable = false)
    private String nombre;
    @Column(length = 300,nullable = false)
    private String descripcion;
    @ManyToOne
    private Category categoria;

    private Date fechaRegistro;
    @Column(length = 20)
    private Double precioVenta;

    @Column(length = 20)
    private Double precioCompra;
    @Column(nullable = false, scale = 2)
    private int stock;
}
