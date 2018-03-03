package br.com.fiap.roupas.bean;

import java.io.Serializable;

public class CupomFiscal implements Serializable {

	private String nomeArquivo;
	private byte[] pdf;

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public byte[] getPdf() {
		return pdf;
	}

	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}

}
