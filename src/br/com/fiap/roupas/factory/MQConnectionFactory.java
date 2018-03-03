package br.com.fiap.roupas.factory;

import java.util.Arrays;

import javax.jms.Connection;

import org.apache.activemq.ActiveMQConnectionFactory;

import br.com.fiap.roupas.constants.MQConstants;
import br.com.fiap.roupas.exceptions.ConnectionRefusedException;

public class MQConnectionFactory {

	public static Connection getConnection() throws ConnectionRefusedException {

		Connection connection = null;
		String url = MQConstants.URL;
		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
			connectionFactory.setTrustAllPackages(true);
			connection = connectionFactory.createConnection();
		} catch (Exception e) {
			System.out.println("An error occured trying to connect to MQ: " + e);
			throw new ConnectionRefusedException();
		}
		return connection;
	}

}
