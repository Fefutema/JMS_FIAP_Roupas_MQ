package br.com.fiap.roupas.tests;

import org.junit.jupiter.api.Test;

import br.com.fiap.roupas.mq.Producer;

class ProducerTest {
	Producer producer = new Producer();
	@Test
	void test() {
		producer.produce(1l);
	}

}
