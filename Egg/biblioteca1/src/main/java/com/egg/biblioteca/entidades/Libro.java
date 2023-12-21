package com.egg.biblioteca.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
@Table
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long isbn;
    @Column
    private String titulo;
    @Column
    private Integer ejemplares;
    @Column
    @Temporal(TemporalType.DATE)
    private Date alta;
    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Editorial editorial;
}
