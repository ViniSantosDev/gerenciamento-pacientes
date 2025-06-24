package br.com.vinisantosdev.gerenciamentopacientes.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDTO {
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate diaRealizadoAula;
}



