package br.com.vinisantosdev.gerenciamentopacientes.dto;


import java.time.LocalDateTime;


public record PatientDTO(String nome, LocalDateTime diaRealizadoAula) {
}



