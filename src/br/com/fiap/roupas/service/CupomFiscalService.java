package br.com.fiap.roupas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import br.com.fiap.roupas.dao.CupomFiscalRepository;
import br.com.fiap.roupas.model.CupomFiscal;

@Validated
public class CupomFiscalService {
	@Autowired
	CupomFiscalRepository cupomFiscalRepository;
	
	public CupomFiscal getCupomByID(final String  id) {
		return this.cupomFiscalRepository.findOne(id);
	}
	public List<CupomFiscal> getAllCupons(final String  id) {
		return this.cupomFiscalRepository.findAll(id);
	}
}
