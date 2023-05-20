package br.com.ViniSantosDev.gerenciamentopacientes.service;

import br.com.ViniSantosDev.gerenciamentopacientes.dto.PacienteDTO;
import br.com.ViniSantosDev.gerenciamentopacientes.entity.Paciente;
import br.com.ViniSantosDev.gerenciamentopacientes.repository.PacienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
}
