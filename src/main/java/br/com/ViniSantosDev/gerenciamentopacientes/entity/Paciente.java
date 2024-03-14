package br.com.ViniSantosDev.gerenciamentopacientes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;


    @Column(name = "Dia", nullable = false)
    private  String diaRealizadoAula;
    
    public String getDiaRealizadoAula() {
		return diaRealizadoAula;
	}
    public String getNome() {
		return nome;
	}
}
