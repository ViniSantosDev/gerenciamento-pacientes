package br.com.ViniSantosDev.gerenciamentopacientes.service;

import br.com.ViniSantosDev.gerenciamentopacientes.dto.PacienteDTO;
import br.com.ViniSantosDev.gerenciamentopacientes.entity.Paciente;
import br.com.ViniSantosDev.gerenciamentopacientes.repository.PacienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    public PacienteDTO postPacientes(PacienteDTO body) {
        Paciente paciente = modelMapper.map(body, Paciente.class);
        Paciente pacienteRegistrado = repository.save(paciente);
        return modelMapper.map(pacienteRegistrado, PacienteDTO.class);
    }

    public Page<PacienteDTO> findAllPacientes() {
        try {
            Pageable pageable = PageRequest.of(0, 100);
            Page<Paciente> pacientes = repository.findAll(pageable);
            return convertToDTO(pacientes);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public Page<PacienteDTO> convertToDTO(Page<Paciente> pacientes) {
        return pacientes.map(item -> this.convertToPacienteDTO(item));
    }
    public PacienteDTO convertToPacienteDTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        dto.setNome(paciente.getNome());
        dto.setDiaRealizadoAula(paciente.getDiaRealizadoAula());
        return dto;
    }
}
