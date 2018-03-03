package br.com.fiap.roupas.facade;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.roupas.exceptions.InvalidOrderException;
import br.com.fiap.roupas.mq.ProducerService;

@Component
public class PedidoFacade {

	@Autowired
	private ProducerService producerService;

	public Boolean getOder(String orderID) throws InvalidOrderException {
		return Optional.ofNullable(producerService.produce(orderID))
				.orElseThrow(InvalidOrderException::new);
	}

}
