package br.com.ViniSantosDev.gerenciamentopacientes.controller;

import br.com.ViniSantosDev.gerenciamentopacientes.dto.PacienteDTO;
import br.com.ViniSantosDev.gerenciamentopacientes.entity.Paciente;
import br.com.ViniSantosDev.gerenciamentopacientes.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("pacientes")
public class PacienteController {


	@Autowired
	private PacienteService service;

	@GetMapping
	public ResponseEntity<Page<PacienteDTO>> getAllFilme() throws Exception {
		try {
			Page<PacienteDTO> result = service.findAllPacientes();
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@PostMapping
	@Transactional
	
	public void enviarExcel(@RequestBody Paciente paciente) throws Exception {
		service.createExcelWithData(paciente);
	}
}