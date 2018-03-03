package br.com.fiap.roupas.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.roupas.model.CupomFiscal;

@Repository
public interface CupomFiscalRepository extends MongoRepository<CupomFiscal, String>{
	CupomFiscal findOne(String id);
	List<CupomFiscal> findAll(String id);
}
