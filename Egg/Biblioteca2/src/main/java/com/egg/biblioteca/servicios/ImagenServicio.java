
package com.egg.biblioteca.servicios;


import com.egg.biblioteca.entidades.Imagen;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.ImagenRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Luciano Otegui
 */
@Service
public class ImagenServicio {
    @Autowired
    private ImagenRepositorio imagenRepositorio;
    
    public Imagen guardar(MultipartFile archivo)throws MiException{//Este tipo de dato MultipartFile es el tipo de archivo en el que se va a almacenar la imagen
        if (archivo != null) {
            try {
                
                Imagen imagen = new Imagen();
                
                imagen.setMime(archivo.getContentType());//se setea haciendo uso del getContentType de nuestro MultiPartFile y lo guardamos en nuestro dato mime
                
                imagen.setNombre(archivo.getName());
                
                imagen.setContenido(archivo.getBytes());
                
                imagenRepositorio.save(imagen);
                
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
    
    public Imagen actualizar(MultipartFile archivo, String IdImagen)throws MiException{
        if (archivo != null) {
            try {
                
                Imagen imagen = new Imagen();
                
                if (IdImagen != null) {
                    Optional<Imagen> respuesta = imagenRepositorio.findById(IdImagen);// optional nos trae la imagen desde el repoditorio
                    
                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();
                    }    
                }
                
                imagen.setMime(archivo.getContentType());//se setea haciendo uso del getContentType de nuestro MultiPartFile y lo guardamos en nuestro dato mime
                
                imagen.setNombre(archivo.getName());
                
                imagen.setContenido(archivo.getBytes());
                
                imagenRepositorio.save(imagen);
                
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
}
