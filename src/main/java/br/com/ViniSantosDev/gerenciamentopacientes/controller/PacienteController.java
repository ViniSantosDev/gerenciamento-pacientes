package br.com.ViniSantosDev.gerenciamentopacientes.controller;

import br.com.ViniSantosDev.gerenciamentopacientes.dto.PacienteDTO;
import br.com.ViniSantosDev.gerenciamentopacientes.entity.Paciente;
import br.com.ViniSantosDev.gerenciamentopacientes.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping()
    public ResponseEntity<PacienteDTO> enviarDadosParaExcel(@RequestBody PacienteDTO body) { //criar exceptions customizadas
        try {
            PacienteDTO result = service.postPacientes(body);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
}
