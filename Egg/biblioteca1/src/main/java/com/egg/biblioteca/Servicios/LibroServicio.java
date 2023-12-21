package com.egg.biblioteca.Servicios;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.repositorios.AutorRepositorio;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import com.egg.biblioteca.repositorios.LibroRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class LibroServicio {
@Autowired
    private LibroRepositorio libroRepositorio;
@Autowired
private AutorRepositorio autorRepositorio;
@Autowired
private EditorialRepositorio editorialRepositorio;
@Transactional // hace un rollback si no se crea el libro commit
    public void crearLibro(Long isbn, String titulo, Integer Ejemplares, String idAutor, String idEditorial){
        var editorial = editorialRepositorio.findById(idEditorial).get();
        var autor = autorRepositorio.findById(idAutor).get();
        var libro = new Libro();
        autor.setId(idAutor);
        editorial.setId(idEditorial);


        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(Ejemplares);
        libro.setAlta(new Date());
        libroRepositorio.save(libro);
    }

    public List<Libro> getLibros(){

       List <Libro> libros = new ArrayList();
        libros = libroRepositorio.findAll();
     return libros;
    }

    public void modificarLibro (Long isbn,String titulo, String idAutor , String idEditorial, Integer ejemplares){

        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        if (respuestaAutor.isPresent()){
           autor = respuestaAutor.get();
        }
        if (respuestaEditorial.isPresent()){
          editorial = respuestaEditorial.get();
        }

        if (respuesta.isPresent()){
            Libro libro = respuesta.get();

            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);


            libroRepositorio.save(libro);
        }
    }
}
