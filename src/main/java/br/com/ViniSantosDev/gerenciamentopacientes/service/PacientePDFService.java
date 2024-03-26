//package br.com.ViniSantosDev.gerenciamentopacientes.service;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import org.springframework.stereotype.Service;
//
//import com.lowagie.text.Document;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.Paragraph;
//
//import br.com.ViniSantosDev.gerenciamentopacientes.entity.Paciente;
//
//@Service
//public class PacientePDFService {
//
//	public void createPdfWithData(Paciente paciente, String outputPatch) throws FileNotFoundException, IOException {
//		Document myPdf = new Document(PageSize.A4);
//
//		try (FileOutputStream fos = new FileOutputStream(outputPatch)) {
//			myPdf.add(new Paragraph("Relatório de Paciente"));
//			myPdf.add(new Paragraph("Dados 1: " + paciente.getNome()));
//			myPdf.add(new Paragraph("Dados 2: " + paciente.getDiaRealizadoAula()));
//		}
//	}
//}
