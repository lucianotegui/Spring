package com.GrupoD.AppServSalud.dominio.entidades;

import com.GrupoD.AppServSalud.utilidades.ObraSocialEnum;
import com.GrupoD.AppServSalud.utilidades.RolEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor 
@Entity
@DiscriminatorValue("paciente")
public class Paciente extends Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;

    @Enumerated(EnumType.STRING)
    private ObraSocialEnum obraSocial;
    
    @Enumerated(EnumType.STRING)
    private RolEnum rol;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    private HistoriaClinica historiaClinica;

}
