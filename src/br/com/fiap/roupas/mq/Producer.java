package br.com.fiap.roupas.mq;

import java.io.File;
import java.nio.file.Files;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import br.com.fiap.roupas.bean.CupomFiscal;
import br.com.fiap.roupas.factory.MQConnectionFactory;
import br.com.fiap.roupas.util.ConfigUtil;

public class Producer {

	public boolean produce(Long idPedido) {

		Connection connection = null;

		try {
			connection = MQConnectionFactory.getConnection();

			String subject = ConfigUtil.getProperty("mq.connection.subject");
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(subject);
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);

			CupomFiscal cupom = new CupomFiscal();
			cupom.setNomeArquivo("Cupom-xxx");
			cupom.setPdf(Files.readAllBytes(new File("d:\\framework\\cupom-xxx.pdf").toPath()));
			
			ObjectMessage cupomMsg = session.createObjectMessage(cupom);
			cupomMsg.setJMSType("OBJECT");
			
			producer.send(cupomMsg);

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

}
