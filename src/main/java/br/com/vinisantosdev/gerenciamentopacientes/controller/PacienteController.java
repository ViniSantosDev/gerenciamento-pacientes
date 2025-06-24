package br.com.vinisantosdev.gerenciamentopacientes.controller;

import br.com.vinisantosdev.gerenciamentopacientes.dto.PatientDTO;
import br.com.vinisantosdev.gerenciamentopacientes.exception.BusinessException;
import br.com.vinisantosdev.gerenciamentopacientes.exception.ErrorIndicator;
//import br.com.vinisantosdev.gerenciamentopacientes.service.ExcelService;
import br.com.vinisantosdev.gerenciamentopacientes.service.PatientUseCase;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patients")
public class PacienteController {

    private final PatientUseCase pacienteService;
    //private final ExcelService excelService;

//    public PacienteController(ExcelService excelService, PatientUseCase pacienteService) {
//        this.excelService = excelService;
//        this.pacienteService = pacienteService;
//    }


    public PacienteController(PatientUseCase pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<Page<PatientDTO>> getAllPacientes() {
        Page<PatientDTO> result = pacienteService.findAll(0, 10);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<String> enviarExcel(@RequestBody PatientDTO dto) throws BusinessException {
//        if (excelService.pacienteJaExiste(dto)) {
//            return ResponseEntity.badRequest().body(new BusinessException(ErrorIndicator.ERROR_INDICATOR_001).getErrorMessage());
//        }
            pacienteService.save(dto);
           // excelService.adicionarPacienteAoExcel(dto);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }