package br.com.fiap.roupas.tests;

import org.junit.jupiter.api.Test;

import br.com.fiap.roupas.mq.Consumer;

class ConfigUtilTest {
	Consumer consumer = new Consumer();
	@Test
	void test() {
		consumer.consume();
	}

}
