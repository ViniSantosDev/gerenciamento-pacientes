package br.com.vinisantosdev.gerenciamentopacientes.mapper;

import br.com.vinisantosdev.gerenciamentopacientes.dto.PatientDTO;
import br.com.vinisantosdev.gerenciamentopacientes.entity.Patient;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    PatientDTO toDto(Patient paciente);
    Patient toEntity(PatientDTO pacienteDTO);
}