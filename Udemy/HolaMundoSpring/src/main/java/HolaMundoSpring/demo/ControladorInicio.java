
package HolaMundoSpring.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Luciano Otegui
 */
@RestController
@Slf4j
public class ControladorInicio {
    
    @GetMapping("/")
    
    public String inicio(){
        log.info("ejecutando el controlador rest");
        return "hola mundo con Spring";
}
}
