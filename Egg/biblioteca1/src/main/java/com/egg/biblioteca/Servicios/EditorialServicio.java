package com.egg.biblioteca.Servicios;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class EditorialServicio {
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    @Transactional // hace un rollback si no se crea el libro commit
    public void crearEditorial(String nombre){
        var editorial = new Editorial();

        editorial.setNombre(nombre);
        editorialRepositorio.save(editorial);
    }
    public List<Editorial> getEditorial(){

        List <Editorial> editoriales = new ArrayList();
        editoriales = editorialRepositorio.findAll();
        return editoriales;
    }
    public void modificarEditorial( String nombre,String id) {
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();

            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
        }
    }
}
