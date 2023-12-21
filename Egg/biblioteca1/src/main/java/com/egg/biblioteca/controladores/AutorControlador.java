
package com.egg.biblioteca.controladores;

import com.egg.biblioteca.Servicios.AutorServicio;
import com.egg.biblioteca.excepciones.MiException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Luciano Otegui
 */

@Controller
@RequestMapping("/autor")//localhost:8080/autor
public class AutorControlador {
    @Autowired
    private AutorServicio autorServicio;
    
    @GetMapping("/registrar")// localhost:8080/autor/registrar
    public String registar(){
        return "autor_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre) {//lo que hace es indicar que es un parametro requerido que va a llegar cuando se ejecute el formulario
        
        try {
            autorServicio.crearAutor(nombre);
        } catch (MiException ex) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE,null, ex);
            return "autor_form.html";
        }
        
        return "index.html";
    }
    
}
