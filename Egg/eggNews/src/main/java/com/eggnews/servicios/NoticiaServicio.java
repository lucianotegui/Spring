
package com.eggnews.servicios;

import com.eggnews.entidades.Noticia;
import com.eggnews.excepciones.MiException;
import com.eggnews.repositorios.NoticiaRepositorio;
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
    public void crearNoticia(String titulo, String cuerpo) throws MiException{
        validar(titulo, cuerpo);
        Noticia noticia = new Noticia();
        
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        //noticia.setFoto(foto);
        //noticia.setFechaDePublicacion(fechaDePublicacion);//fijarse en el tipo de dato
        
        noticiaRepositorio.save(noticia);
    }
    
    @Transactional
    public void eliminar(Long id) throws MiException{
        
        Noticia noticia = noticiaRepositorio.getById(id);
        
        noticiaRepositorio.delete(noticia);
    }
    
    public void modificarNoticia(Long id, String titulo, String cuerpo) throws MiException{
        validar(titulo, cuerpo);
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);
    }
    
    private void validar(String titulo, String cuerpo)throws MiException{
            if (titulo == null || titulo.isEmpty()) {
                throw new MiException("El titulo no puede estar vacio");
            }
            
            if (cuerpo == null || cuerpo.isEmpty()) {
                throw new MiException("El cuerpo de la noticia tiene que estar presente, para ser una noticia");
            }
            
            //if (foto == null || foto.isEmpty()) {
                //throw new MiException("Debe haber una foto descriptiva");
            
            //}
           
            
        
    }
}