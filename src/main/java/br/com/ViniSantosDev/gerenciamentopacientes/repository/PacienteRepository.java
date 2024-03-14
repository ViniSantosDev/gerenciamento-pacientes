package br.com.ViniSantosDev.gerenciamentopacientes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ViniSantosDev.gerenciamentopacientes.entity.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	
	  Optional<Paciente> findByNomeAndDiaRealizadoAula(String nome, String diaRealizadoAula);

	    default boolean pacienteJaRegistradoNoDia(String nome, String diaRealizadoAula) {
	        return findByNomeAndDiaRealizadoAula(nome, diaRealizadoAula).isPresent();
	    }

}
