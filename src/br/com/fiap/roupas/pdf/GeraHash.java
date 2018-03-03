package br.com.fiap.roupas.pdf;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeraHash {
	
	public String gerarHash() throws Exception {
		String s="aula_29scj_sabadao_da_alegria";
		try {
		    MessageDigest m = MessageDigest.getInstance("MD5");
		 
		    m.update( s.getBytes(), 0 , s.length() );
		             
		    byte[] digest = m.digest();
		         
		    String hexa = new BigInteger(1,digest).toString(16);
		             
		    return hexa;
		} 
		catch ( NoSuchAlgorithmException e ) 
		{
		    throw new Exception();
		}
	}

}
