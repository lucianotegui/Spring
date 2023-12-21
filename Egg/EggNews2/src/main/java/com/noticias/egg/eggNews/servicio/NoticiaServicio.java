
package com.noticias.egg.eggNews.servicio;

import com.noticias.egg.eggNews.Entidades.Noticia;
import com.noticias.egg.eggNews.Repositorio.NoticiaRepositorio;
import com.noticias.egg.eggNews.excepciones.MiException;
import jakarta.transaction.Transactional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Luciano Otegui
 */
@Service
public class NoticiaServicio {
    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    
    @Transactional
    public void crearNoticias(String titulo, String cuerpo)throws MiException{
        validar(titulo,cuerpo);
        Noticia noticia = new Noticia();
        
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        
        noticiaRepositorio.save(noticia);
    }
    
    @Transactional
    public void darDeBaja(Long id){
       Optional<Noticia> respuesta = noticiaRepositorio.findById(id);
       
        if (respuesta.isPresent() ) {
            Noticia noticia = respuesta.get();
            noticia.setStatus(false);
            noticiaRepositorio.save(noticia);
        }
    }
    
    public void modificarNoticia(Long id,String titulo, String cuerpo)throws MiException{
        validar(titulo, cuerpo);
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            noticiaRepositorio.save(noticia);
        }
    }
    
    private void validar(String titulo, String cuerpo)throws MiException{
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("La noticia debe tener un titulo");
        }
        if (cuerpo.isEmpty() ||cuerpo == null) {
            throw new MiException("La notica debe contener un cuerpo");
        }
    }
    
}
