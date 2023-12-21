package com.eggnews.repositorios;

import com.eggnews.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

/**
 *
 * @author Luciano Otegui
 */
public interface NoticiaRepositorio extends JpaRepository<Noticia, Long>{
    
    //@Query("SELECT n FROM Noticia n WHERE n.titulo = :titulo")
    //public Noticia buscarPorTitulo(@Param("titulo") String titulo);
}
