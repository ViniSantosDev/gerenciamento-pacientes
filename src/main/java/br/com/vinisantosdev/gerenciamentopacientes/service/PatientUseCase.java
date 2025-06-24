package br.com.vinisantosdev.gerenciamentopacientes.service;

import br.com.vinisantosdev.gerenciamentopacientes.dto.PatientDTO;
import br.com.vinisantosdev.gerenciamentopacientes.mapper.PacienteMapper;
import br.com.vinisantosdev.gerenciamentopacientes.entity.Patient;
import br.com.vinisantosdev.gerenciamentopacientes.repository.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientUseCase {

    private final PacienteRepository repository;

    PatientUseCase(PacienteRepository repository) {
        this.repository = repository;

    }

    public PatientDTO save(PatientDTO body) {
        Patient entity = PacienteMapper.INSTANCE.toEntity(body);
        Patient saved = repository.save(entity);
        return PacienteMapper.INSTANCE.toDto(saved);
    }

    public Page<PatientDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Patient> patients = repository.findAll(pageable);
        return patients.map(PacienteMapper.INSTANCE::toDto);
    }

}
