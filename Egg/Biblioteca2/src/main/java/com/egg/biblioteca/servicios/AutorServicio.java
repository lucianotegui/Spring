
package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.AutorRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Luciano Otegui
 */
@Service
public class AutorServicio {
    
    @Autowired
    AutorRepositorio autorRepositorio;
    
    @Transactional// hace un rollback si no se crea el libro commit
    public void crearAutor(String nombre) throws MiException{//solo el nombre porque el id se genera automaticamente
        validar(nombre);
        
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor);
    }
    
    public List<Autor> listarAutores(){
        List<Autor> autores = new ArrayList();
        autores = autorRepositorio.findAll();
        return autores;
    }
    
    public void modificarAutor(String nombre, String id ) throws MiException{
            validar(nombre);
            Optional<Autor> respuesta = autorRepositorio.findById(id);
            
            if (respuesta.isPresent()) {
                Autor autor = respuesta.get();
                
                autor.setNombre(nombre);
                
                autorRepositorio.save(autor);
            
        }
    }    
    
    public Autor getOne(String id){
        return autorRepositorio.getOne(id);
    }
    
    
    @Transactional
    public void eliminar(String id) throws MiException{
        
        Autor autor = autorRepositorio.getById(id);
        
        autorRepositorio.delete(autor);

    }
    private void validar(String nombre) throws MiException{
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede ser nulo o estar vacio");
        }
    }
}
