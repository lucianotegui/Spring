
package com.egg.biblioteca.entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Luciano Otegui
 */
@Entity
public class Editorial {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;

    public Editorial() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
