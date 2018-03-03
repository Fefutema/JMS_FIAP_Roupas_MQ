package br.com.fiap.roupas.tests;

import org.junit.jupiter.api.Test;

import br.com.fiap.roupas.model.CupomFiscal;
import br.com.fiap.roupas.pdf.PDFGenerator;

class PDFGeneratorTest {
	PDFGenerator pdf = new PDFGenerator();
	@Test
	void test() {
		pdf.createPDF(new CupomFiscal());
	}

}
