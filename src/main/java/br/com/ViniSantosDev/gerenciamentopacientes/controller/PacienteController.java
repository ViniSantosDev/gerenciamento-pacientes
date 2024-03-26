package br.com.ViniSantosDev.gerenciamentopacientes.controller;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ViniSantosDev.gerenciamentopacientes.entity.Paciente;
import br.com.ViniSantosDev.gerenciamentopacientes.service.PacienteExcelService;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

	@Autowired
	private PacienteExcelService excelService;


	@PostMapping("/excel")
	@Transactional
	public ResponseEntity<Paciente> enviarExcel(@RequestBody Paciente paciente) throws Exception {
		excelService.createExcelWithData(paciente);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}