package br.com.fiap.roupas.mq;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import br.com.fiap.roupas.exceptions.ConnectionRefusedException;
import br.com.fiap.roupas.factory.MQConnectionFactory;

public class Producer {

	private static String SUBJECT = "Cupons";
	
	public static void main(String[] args) throws JMSException, ConnectionRefusedException {
		
		Connection connection = MQConnectionFactory.getConnection();
		
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination destination = session.createQueue(SUBJECT);
		MessageProducer producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		
		BytesMessage pdf = session.createBytesMessage();
		pdf.setJMSType("BYTES");
		
		try {
			pdf.writeBytes(
					Files.readAllBytes(
							new File("d:\\framework\\cupom-xxx.pdf").toPath()));
			
			pdf.setStringProperty("fileExtension", "pdf");
            pdf.setStringProperty("fileName", "Cupom-xxx");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		producer.send(pdf);
		
		connection.close();
		
	}
	
}
