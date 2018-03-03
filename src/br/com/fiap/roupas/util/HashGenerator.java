package br.com.fiap.roupas.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {

	public String generateHash() throws Exception {
		String s = "cupomfiap";
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");

			m.update(s.getBytes(), 0, s.length());

			byte[] digest = m.digest();

			String hexa = new BigInteger(1, digest).toString(16);

			return hexa;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception();
		}
	}

}
