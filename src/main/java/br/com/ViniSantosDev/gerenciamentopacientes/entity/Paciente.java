package br.com.ViniSantosDev.gerenciamentopacientes.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Calendar;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Setter
@Getter
@Table
@Entity
public class Paciente extends BaseEntity {

    @Column(name = "nome", nullable = false)
    private String nome;


    @Column(name = "Dia", nullable = false)
    private  String diaRealizadoAula;
}
