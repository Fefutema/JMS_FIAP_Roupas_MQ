package br.com.fiap.roupas.mq;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.roupas.constants.MQConstants;
import br.com.fiap.roupas.exceptions.ConnectionRefusedException;
import br.com.fiap.roupas.exceptions.InvalidIDException;
import br.com.fiap.roupas.factory.MQConnectionFactory;
import br.com.fiap.roupas.model.CupomFiscal;
import br.com.fiap.roupas.service.CupomFiscalService;
import br.com.fiap.roupas.util.ConfigUtil;

public class Producer {

	@Autowired
	private CupomFiscalService cupomFiscalService;

	public boolean produce(String idPedido) {
		
		Connection connection = null;
		List<CupomFiscal> cupomFiscalList = null;

		try {	
			connection = this.startConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = createQueue(session);
			MessageProducer producer = this.createMessageProducer(session, destination);
			
			this.getCuponsList(Strign idPedido);
			
			for (CupomFiscal cupom : cupomFiscalList) {
				sendCupom(session, producer, cupom);
			}

			return true;

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				connection.close();
			} catch (JMSException e) {
				System.out.println("Error occurred closing connection");
			}
		}
		return false;

	}

	private void getCuponsList(String idPedido) {

		if (isSearchParameter(idPedido)) {
			cupomFiscalList = cupomFiscalService.getAllCupons(idPedido);
		}else {
			cupomFiscalList = Arrays.asList(Optional.ofNullable(cupomFiscalService.getCupomByID(idPedido))
					.orElseThrow((InvalidIDException::new);
		}
		
	}

	private Destination createQueue(Session session) throws IOException, JMSException {
		String subject = ConfigUtil.getProperty("mq.connection.subject");
		Destination destination = session.createQueue(subject);
		return destination;
	}

	private MessageProducer createMessageProducer(Session session, Destination destination) throws JMSException {
		MessageProducer producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		return producer;
	}

	private Connection startConnection() throws ConnectionRefusedException, JMSException {
		Connection connection;
		connection = MQConnectionFactory.getConnection();
		connection.start();
		return connection;
	}

	private void sendCupom(Session session, MessageProducer producer, CupomFiscal cupom)
			throws IOException, JMSException {
		cupom.setNomeArquivo("Cupom-xxx");
		cupom.setPdf(Files.readAllBytes(new File("d:\\framework\\cupom-xxx.pdf").toPath()));

		ObjectMessage cupomMsg = session.createObjectMessage(cupom);
		cupomMsg.setJMSType("OBJECT");

		producer.send(cupomMsg);
	}

	private boolean isSearchParameter(String idPedido) throws InvalidIDException {
		Boolean result = Boolean.FALSE;

		Optional.ofNullable(idPedido).orElseThrow((InvalidIDException::new));
		if (MQConstants.PARAMETRO_BUSCAR_TODOS.equals(idPedido)) {
			result = Boolean.TRUE;
		}

		return result;
	}

}
