package br.com.ViniSantosDev.gerenciamentopacientes.repository;

import br.com.ViniSantosDev.gerenciamentopacientes.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
