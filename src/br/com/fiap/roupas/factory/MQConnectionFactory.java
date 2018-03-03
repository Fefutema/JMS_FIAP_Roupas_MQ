package br.com.fiap.roupas.factory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;

import br.com.fiap.roupas.exceptions.ConnectionRefusedException;

public class MQConnectionFactory {

	public static Connection getConnection(String url) throws ConnectionRefusedException {

		Connection connection = null;

		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
			connection = connectionFactory.createConnection();
		} catch (Exception e) {
			System.out.println("An error occured trying to connect to MQ: " +e);
			throw new ConnectionRefusedException();
		}
		return connection;
	}

}
