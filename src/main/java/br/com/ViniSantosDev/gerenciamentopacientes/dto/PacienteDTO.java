package br.com.ViniSantosDev.gerenciamentopacientes.dto;

import br.com.ViniSantosDev.gerenciamentopacientes.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
public class PacienteDTO {

    private String nome;
    private String diaRealizadoAula;

    public PacienteDTO() {

    }
    public PacienteDTO(Paciente paciente) {
        this.nome = paciente.getNome();
        this.diaRealizadoAula = paciente.getDiaRealizadoAula();
    }
    
    public void setDiaRealizadoAula(String diaRealizadoAula) {
		this.diaRealizadoAula = diaRealizadoAula;
	}
    public void setNome(String nome) {
		this.nome = nome;
	}
}


