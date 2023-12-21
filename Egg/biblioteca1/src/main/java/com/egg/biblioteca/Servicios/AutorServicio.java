package com.egg.biblioteca.Servicios;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.AutorRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServicio {
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Transactional // hace un rollback si no se crea el libro commit
    public void crearAutor(String nombre) throws MiException{
        var autor = new Autor();
        validar(nombre);
        autor.setNombre(nombre);
        autorRepositorio.save(autor);
    }
    public List<Autor> getAutor(){
        return autorRepositorio.findAll();
    }
    public void modificarAutor( String nombre,String id){
        Optional<Autor>  respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()){
            Autor autor = respuesta.get();

            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }

    }
    private void validar(String nombre) throws MiException{
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede ser nulo o estar vacio");
        }
    }
}
