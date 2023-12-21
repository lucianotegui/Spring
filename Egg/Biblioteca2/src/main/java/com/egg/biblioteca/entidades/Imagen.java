
package com.egg.biblioteca.entidades;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Luciano Otegui
 */
@Entity
@Data
public class Imagen {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String mime;
    private String nombre;
    //Con la anotacion @Lob, le decimos a spring que el archivo puede ser grande
    @Lob  @Basic(fetch = FetchType.LAZY)//Con la notacion FetchType.LAZY, el contenido (foto) se va a cargar SOLAMENTE CUADO LO PEDIMOS, para que las Querys sean mas livianas
    private byte[] contenido;
}
