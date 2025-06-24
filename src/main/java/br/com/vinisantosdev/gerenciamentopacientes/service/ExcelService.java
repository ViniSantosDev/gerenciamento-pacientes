package br.com.vinisantosdev.gerenciamentopacientes.service;

import br.com.vinisantosdev.gerenciamentopacientes.dto.PatientDTO;
import br.com.vinisantosdev.gerenciamentopacientes.exception.BusinessException;
import br.com.vinisantosdev.gerenciamentopacientes.exception.ErrorIndicator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ExcelService {

    @Value("${excel.file.path}")
    private String excelFilePath;

    public boolean pacienteJaExiste(PatientDTO pacienteDTO) throws BusinessException{
        validarPaciente(pacienteDTO);

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Pacientes");
            if (sheet == null) return false;

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                Cell nomeCell = row.getCell(0);
                Cell diaCell = row.getCell(1);

                if (nomeCell == null || diaCell == null) continue;

                String nome = nomeCell.getStringCellValue().trim();
                String dia = diaCell.getStringCellValue().trim();

                if (pacienteDTO.getNome().equalsIgnoreCase(nome)
                        && pacienteDTO.getDiaRealizadoAula().toString().equalsIgnoreCase(dia)) {
                    return true;
                }
            }

        } catch (IOException e) {
            throw new BusinessException(
                    ErrorIndicator.ERROR_INDICATOR_003);
        }

        return false;
    }

    public void adicionarPacienteAoExcel(PatientDTO pacienteDTO) throws BusinessException{
        validarPaciente(pacienteDTO);

        try {
            Workbook workbook;
            Sheet sheet;

            File file = new File(excelFilePath);

            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    workbook = new XSSFWorkbook(fis);
                }
            } else {

                workbook = new XSSFWorkbook();
            }

            sheet = workbook.getSheet("Pacientes");
            if (sheet == null) {
                sheet = workbook.createSheet("Pacientes");
                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("Nome");
                header.createCell(1).setCellValue("DiaRealizadoAula");
            }

            int lastRow = sheet.getLastRowNum() + 1;
            Row newRow = sheet.createRow(lastRow);

            newRow.createCell(0).setCellValue(pacienteDTO.getNome());
            newRow.createCell(1).setCellValue(pacienteDTO.getDiaRealizadoAula());

            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }

            workbook.close();

        } catch (IOException e) {
            throw new BusinessException(
                    ErrorIndicator.ERROR_INDICATOR_003);
        }
    }

    private void validarPaciente(PatientDTO pacienteDTO) throws BusinessException {
        if (pacienteDTO == null || pacienteDTO.getNome() == null) {
            throw new BusinessException(
                    ErrorIndicator.ERROR_INDICATOR_001);
        }
    }
}
