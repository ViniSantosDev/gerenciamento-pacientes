package br.com.vinisantosdev.gerenciamentopacientes.repository;

import br.com.vinisantosdev.gerenciamentopacientes.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Patient, Long> {

}
