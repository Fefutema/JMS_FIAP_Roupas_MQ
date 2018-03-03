package br.com.fiap.roupas.mq;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import br.com.fiap.roupas.factory.MQConnectionFactory;
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

			if (message instanceof BytesMessage) {
				BytesMessage pdf = (BytesMessage) message;
				
				System.out.println("Receiving..." + pdf + "'");
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
