package br.com.fiap.roupas.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {

	public static void main(String[] args) {
		
		Document document = new Document();
		
		try {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d:\\framework\\Cupom-teste.pdf"));
			document.open();
			
			Paragraph nomeEmpresa = new Paragraph("NOME DA EMPRESA");
			nomeEmpresa.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph endereco = new Paragraph("ENDERECO");
			endereco.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph cidadeEstado = new Paragraph("SÃO PAULO/SP");
			cidadeEstado.setAlignment(Element.ALIGN_CENTER);
			
			document.add(nomeEmpresa);
			document.add(endereco);
			document.add(cidadeEstado);
			
			document.add(new Paragraph("\n\n\n"));
			
			document.add(new Paragraph("CNPJ: 11.111.111/111-11"));
			document.add(new Paragraph("IE: 222.222.222.222"));
			document.add(new Paragraph("IM: 3.333.333-3"));
			
			document.add(new Paragraph("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"));
			document.add(new Paragraph("\n"));
			
			PdfPTable tableDtCoo = new PdfPTable(4);
			tableDtCoo.setWidthPercentage(100);		
			
			tableDtCoo.addCell(getCell(format.format(LocalDate.now()), PdfPCell.ALIGN_LEFT));
			tableDtCoo.addCell(getCell(formatTime.format(LocalTime.now()), PdfPCell.ALIGN_LEFT));
			tableDtCoo.addCell(getCell("CCF: 010333", PdfPCell.ALIGN_LEFT));
			tableDtCoo.addCell(getCell("COO: 123456", PdfPCell.ALIGN_RIGHT));
			
			document.add(tableDtCoo);
			
			document.add(new Paragraph("\n\n"));
			document.add(new Paragraph("CUPOM FISCAL"));
			document.add(new Paragraph("\n\n"));
			
			PdfPTable tableHeader = new PdfPTable(9);
			tableHeader.setWidthPercentage(100);		
			
			tableHeader.addCell(getCell("ITEM", PdfPCell.ALIGN_LEFT));
			tableHeader.addCell(getCell("CÓDIGO", PdfPCell.ALIGN_LEFT));
			tableHeader.addCell(getCell("DESCRIÇÃO", PdfPCell.ALIGN_LEFT));
			tableHeader.addCell(getCell("QTD.", PdfPCell.ALIGN_LEFT));
			tableHeader.addCell(getCell("UN.", PdfPCell.ALIGN_LEFT));
			tableHeader.addCell(getCell("VL UNIT(R$)", PdfPCell.ALIGN_LEFT));
			tableHeader.addCell(getCell("ST", PdfPCell.ALIGN_LEFT));
			tableHeader.addCell(getCell("VL", PdfPCell.ALIGN_LEFT));
			tableHeader.addCell(getCell("ITEM(R$)", PdfPCell.ALIGN_LEFT));
			
			document.add(tableHeader);
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			document.close();
		}
		
		
		
	}
	
	public static PdfPCell getCell(String text, int alignment) {
	    PdfPCell cell = new PdfPCell(new Phrase(text));
	    cell.setPadding(0);
	    cell.setHorizontalAlignment(alignment);
	    cell.setBorder(PdfPCell.NO_BORDER);
	    return cell;
	}
	
}
