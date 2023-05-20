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
@Table(name = "paciente")
@Entity
public class Paciente {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;


    @Column(name = "Dia")
    private Calendar diaRealizadoAula;
}
