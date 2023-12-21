
package com.egg.biblioteca;

import com.egg.biblioteca.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Luciano Otegui
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity // En lugar de @EnableGlobalMethodSecurity //@deprecated
public class SeguridadWeb {
   
    //@PreAuthorize("hasRole('ROLE_ADMIN')")

    @Autowired
    public UsuarioServicio usuarioServicio;

    // Encriptar contraseñas
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {//Cuando se registra un usuario
        auth.userDetailsService(usuarioServicio)// con esto se hace la autenticacion del usuario
                .passwordEncoder(new BCryptPasswordEncoder());// con esto se le pasa el codificador de pasword(se encripta)
    }
     
    
    @Bean //SPRING 3 EN ADELANTE
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
            .authorizeHttpRequests((authz) -> authz
                    .requestMatchers("/admin/*").hasRole("ADMIN")
                    .requestMatchers("/login", "/registrar")
                    .permitAll()
                    .requestMatchers("/css/*", "/js/*", "/img/*", "/**")
                    .permitAll()
            ).formLogin((login)->login
                    .loginPage("/login")
                    .loginProcessingUrl("/logincheck")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/inicio", true)
                    .permitAll()
            ).logout((logout)->logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .permitAll()
            ).csrf((csrf)-> csrf.disable());
        return http.build();
    }
}







// Direcciones permitidas y filtros
   /* @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> {
                    csrf.disable();
                })
                // Direcciones permitidas
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/admin/*").hasRole("ADMIN");
                    auth.requestMatchers("/login", "/registrar").permitAll();
                    auth.requestMatchers("/css/*", "/js/*", "/img/*");
                    auth.requestMatchers("/css/*", "/js/*", "/img/*", "/registrar", "/registro")
                            .permitAll();
                    auth.anyRequest().authenticated();
                })
                // Formulario de login
                .formLogin(form -> {
                    form.loginPage("/login"); // Url de la pagina de login")
                    form.loginProcessingUrl("/logincheck"); // Url del action del formulario
                    form.usernameParameter("email"); // Nombre del input del formulario")
                    form.passwordParameter("password"); // Nombre del input del formulario")
                    form.defaultSuccessUrl("/inicio", true); // Url de inicio correcto
                    // form.successHandler(successHandler()); // Redirect al hacer login correcto ↓
                    // Método abajo
                    form.permitAll();
                })
                .logout(logout -> {
                    logout.logoutUrl("/logout"); // Url de logout
                    logout.logoutSuccessUrl("/"); // Url de re direccionamiento al hacer logout
                    logout.permitAll();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS); // ALWAYS, IF_REQUIRED, NEVER,
                                                                                 // STATELESS
                    session.maximumSessions(1); // Máximo de sesiones permitidas
                    session.invalidSessionUrl("/"); // Url de re direccionamiento cuando se cierra sesión
                    session.sessionFixation(sessionFixation -> {
                        // migrateSession() - newSession() - none()
                        sessionFixation.migrateSession();
                    });
                })
                .build();
    }*/
    
