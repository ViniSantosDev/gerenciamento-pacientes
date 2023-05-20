package br.com.ViniSantosDev.gerenciamentopacientes.controller;

import br.com.ViniSantosDev.gerenciamentopacientes.dto.PacienteDTO;
import br.com.ViniSantosDev.gerenciamentopacientes.entity.Paciente;
import br.com.ViniSantosDev.gerenciamentopacientes.service.PacienteService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    private static final String FILE_PATH = "C:\\Excel\\teste.xlsx";

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    private PacienteService service;

    @PostMapping()
    public ResponseEntity<PacienteDTO> criarPaciente(@RequestBody PacienteDTO body) { //criar exceptions customizadas
        try {
            PacienteDTO result = service.postPacientes(body);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @PostMapping("/excel")
    @Transactional
    public ResponseEntity<String> enviarExcel(@RequestBody Paciente paciente) {

        try {
            // Verifica se o paciente já existe no arquivo
            if (pacienteJaExiste(paciente)) {
                return ResponseEntity.badRequest().body("O paciente já existe.");
            }

            entityManager.persist(paciente);
            FileInputStream fileIn = null;
            Workbook workbook;
            Sheet sheet;


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

            return ResponseEntity.ok("Paciente criado com sucesso e dados integrados ao Excel.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o paciente.");
        }
    }


    //TODO: talvez colocar essa logica no service
    private static boolean pacienteJaExiste(Paciente paciente) throws IOException {
        FileInputStream fileIn = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fileIn);
        Sheet sheet = workbook.getSheet("Pacientes");

        for (Row row : sheet) {
            Cell nomeCell = row.getCell(0);
            Cell dataAulaCell = row.getCell(1);
            if (nomeCell != null && dataAulaCell != null) {
                String nome = nomeCell.getStringCellValue();
                String dataAula = nomeCell.getStringCellValue();

                if (nome.equals(paciente.getNome()) && dataAula.equals(paciente.getDiaRealizadoAula())) {
                    return true;
                }
            }
        }

        return false;
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
