
package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.*;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.AutorRepositorio;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import java.util.Date;
import org.springframework.stereotype.Service;
import com.egg.biblioteca.repositorios.LibroRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Luciano Otegui
 */
@Service
public class LibroServicio {
    
    @Autowired//se le indica al servidor de aplicaciones que esta variable va a ser inicializada por el(inyeccion de dependencias)
    private LibroRepositorio libroRepositorio;
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    // todo metodo que genere modificaciones permanentes en la BD deben ser anotados como @Transactional
    @Transactional// si el metodo se ejecuta sin lanzar excepciones, se realiza un commit a la BD y se aplican los datos los cambios, si no hay un roll back y queda sin efecto
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException{
        validar(isbn, titulo, idAutor, idEditorial, ejemplares);
        Autor autor = autorRepositorio.findById(idAutor).get();//.get nos devuelve el autor que encuentra por id
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        Libro libro = new Libro();
        
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        libroRepositorio.save(libro);// recibe una entidad por parametro y la persiste (guarda) en la BD
    }
    
    public List<Libro> listarLibros(){
        List<Libro> libros = new ArrayList();
        libros = libroRepositorio.findAll();
        return libros;
    }
    
    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException{
        validar(isbn, titulo, idAutor, idEditorial, ejemplares);
        
        //Libro libro = libroRepositorio.findById(isbn).get();// antes era asi pero ahora se usara optional
        
        // optional es un objeto contenedor que puede contener o no un valor no nulo,
        //si el valor esta precente devuelve true y nos devuelve el valor con el metodo get
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
        
        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        
        if (respuestaAutor.isPresent()) {
            autor = respuestaAutor.get();
        }
        
        if (respuestaEditorial.isPresent()) {
            editorial = respuestaEditorial.get();
        }
        
        if (respuesta.isPresent()) {
            
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            
            libro.setAutor(autor);
            
            libro.setEditorial(editorial);
            
            libro.setEjemplares(ejemplares);
            
            libroRepositorio.save(libro);
        }
        
    }
    public Libro getOne(long isbn){
         Optional<Libro> respuesta = libroRepositorio.findById(isbn);
         return respuesta.get();
    }
    
    private void validar(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException{
        if (isbn == null) {
            throw new MiException("El isbn no puede ser nulo");
        }
        
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("El titulo no puede ser nulo o estar vacio");
        }
        
        if (ejemplares == null) {
            throw new MiException("Ejemplares no puede ser nulo");
        }
        
        if (idAutor.isEmpty() || idAutor == null) {
            throw new MiException("El autor no puede ser nulo, ni estar vacio");
        }
        
        if (idEditorial.isEmpty() || idEditorial == null) {
            throw new MiException("La editorial no puede ser nulo, ni estar vacio");
        }
    }
}
