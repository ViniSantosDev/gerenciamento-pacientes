package br.com.ViniSantosDev.gerenciamentopacientes.dto;

import br.com.ViniSantosDev.gerenciamentopacientes.entity.Paciente;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Setter
@Getter
public class PacienteDTO {

    public PacienteDTO() {

    }

    public PacienteDTO(Paciente paciente) {
        this.nome = paciente.getNome();
        this.diaRealizadoAula = paciente.getDiaRealizadoAula();
    }

    private String nome;
    private Calendar diaRealizadoAula;


}


