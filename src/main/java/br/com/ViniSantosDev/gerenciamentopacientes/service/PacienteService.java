package br.com.ViniSantosDev.gerenciamentopacientes.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import org.apache.bcel.Repository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ViniSantosDev.gerenciamentopacientes.dto.PacienteDTO;
import br.com.ViniSantosDev.gerenciamentopacientes.entity.Paciente;
import br.com.ViniSantosDev.gerenciamentopacientes.repository.PacienteRepository;

@Service
public class PacienteService {

	private static final String FILE_PATH = "C:\\Excel\\teste.xlsx";

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

	public void createExcelWithData(Paciente paciente) throws Exception {

		try {
			pacienteJaExiste(paciente);

			FileInputStream fileIn = null;
			Workbook workbook;
			Sheet sheet;

			repository.save(paciente);

			if (arquivoExiste()) {
				fileIn = new FileInputStream(FILE_PATH);
				workbook = new XSSFWorkbook(fileIn);
				sheet = workbook.getSheet("Pacientes");

			} else {
				workbook = new XSSFWorkbook();
				sheet = workbook.createSheet("Pacientes");
			}
			int rowCount = sheet.getLastRowNum();
			Row dataRow = sheet.createRow(rowCount + 1);
			dataRow.createCell(0).setCellValue(paciente.getNome());
			dataRow.createCell(1).setCellValue(paciente.getDiaRealizadoAula().toString());

			FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
			workbook.write(fileOut);

			fileOut.close();
			fileIn.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pacienteJaExiste(Paciente paciente) throws Exception {

		if (repository.pacienteJaRegistradoNoDia(paciente.getNome(), paciente.getDiaRealizadoAula())) {
			throw new Exception("PACIENTE JÁ EXISTE NA BASE");
		}
	}

	private static boolean arquivoExiste() {
		try {
			FileInputStream fileIn = new FileInputStream(FILE_PATH);
			fileIn.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
