package br.com.fiap.roupas.test;

import br.com.fiap.roupas.mq.Producer;

class Test {
	Producer prod = new Producer();
	@org.junit.jupiter.api.Test
	void test() {
		prod.produce("-1");
	}

}
