package br.com.ViniSantosDev.gerenciamentopacientes.dto;

import br.com.ViniSantosDev.gerenciamentopacientes.entity.Paciente;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PacienteDTO {


    private String nome;
    private String diaRealizadoAula;

    public PacienteDTO() {

    }
    public PacienteDTO(Paciente paciente) {
        this.nome = paciente.getNome();
        this.diaRealizadoAula = paciente.getDiaRealizadoAula();
    }
}


