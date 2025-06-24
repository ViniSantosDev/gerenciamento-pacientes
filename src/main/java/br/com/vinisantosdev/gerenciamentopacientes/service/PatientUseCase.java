package br.com.vinisantosdev.gerenciamentopacientes.service;

import br.com.vinisantosdev.gerenciamentopacientes.dto.PatientDTO;
import br.com.vinisantosdev.gerenciamentopacientes.entity.Patient;
import br.com.vinisantosdev.gerenciamentopacientes.repository.PacienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientUseCase {

    private final PacienteRepository repository;
    private final ModelMapper modelMapper;

    PatientUseCase(PacienteRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;

    }

    public PatientDTO save(PatientDTO body) {
        Patient entity = modelMapper.map(body, Patient.class);
        Patient saved = repository.save(entity);
        return modelMapper.map(saved, PatientDTO.class);
    }

    public Page<PatientDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Patient> patients = repository.findAll(pageable);
        return patients.map(patient -> modelMapper.map(patient, PatientDTO.class));
    }

}
