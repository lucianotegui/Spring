
package com.egg.biblioteca.repositorios;

import com.egg.biblioteca.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Luciano Otegui
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
// se hace un select de u de todos los usuarios, donde u.email sea igual que el email que nos llega por parametro    
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario  buscarPorEmail(@Param("email")String email);
}
