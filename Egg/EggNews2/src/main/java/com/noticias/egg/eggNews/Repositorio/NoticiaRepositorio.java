
package com.noticias.egg.eggNews.Repositorio;

import com.noticias.egg.eggNews.Entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Luciano Otegui
 */
@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, Long>{
    
}
