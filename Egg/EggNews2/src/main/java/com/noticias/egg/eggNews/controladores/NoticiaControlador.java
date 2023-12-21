
package com.noticias.egg.eggNews.controladores;

import com.noticias.egg.eggNews.servicio.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Luciano Otegui
 */
@Controller
@RequestMapping("/")
public class NoticiaControlador {
    
    @Autowired
    private NoticiaServicio noticiaServicio;
    
    @GetMapping("/")
            public String index(){
                return "index.html";
            }
            
    /*@GetMapping("/noticia") 
    public String noticia(){
        return (noticia.html);
    }*/
}
