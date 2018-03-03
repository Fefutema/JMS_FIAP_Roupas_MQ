package br.com.fiap.roupas.mq;

import java.io.FileOutputStream;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import br.com.fiap.roupas.factory.MQConnectionFactory;
import br.com.fiap.roupas.model.CupomFiscal;
import br.com.fiap.roupas.util.ConfigUtil;

public class Consumer {
	public void consume() {
		Connection connection = null;

		try {
			String subject = ConfigUtil.getProperty("mq.connection.subject");

			connection = MQConnectionFactory.getConnection();
			connection.start();
			Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(subject);
			MessageConsumer consumer = session.createConsumer(destination);
			Message message = consumer.receive();

			if (message instanceof ObjectMessage) {
				ObjectMessage cupomMsg = (ObjectMessage) message;
				Object object = cupomMsg.getObject();
				
				CupomFiscal cupom = (CupomFiscal) object;
				
				try (FileOutputStream fos = new FileOutputStream("d:\\framework\\lidos\\cupom-xxx.pdf")) {
					   fos.write(cupom.getPdf());
					   fos.flush();
				}
				
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				connection.close();
			} catch (JMSException e) {
				System.out.println("Error occurred closing connection");
			}
		}
	}

}
