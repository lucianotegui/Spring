package com.egg.biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PortalControlador {
    @GetMapping(value = "/")
    public String saludo(){
        return "index.html";
    }
}
