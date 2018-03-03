package br.com.fiap.roupas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.roupas.exceptions.InvalidOrderException;
import br.com.fiap.roupas.facade.PedidoFacade;

@RestController
@RequestMapping(value = "api/v1/campanha")

public class PedidoController {

	@Autowired
	private PedidoFacade pedidoFacade;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getOrderID(@RequestBody @Valid String idPedido) {
		try {
			this.pedidoFacade.getOder(idPedido);
			return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);

		} catch (InvalidOrderException e) {
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.NOT_FOUND);
		}
	}

}
