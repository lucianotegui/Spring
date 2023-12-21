package com.eggnews.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Luciano Otegui
 */
@Controller
@RequestMapping("/")// configura cual esurl que va a escuchar a este controlador(en este caso lee a partir de la barra "/")
public class InicioControlador {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}
