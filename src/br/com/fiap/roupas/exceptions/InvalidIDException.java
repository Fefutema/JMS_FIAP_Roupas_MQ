package br.com.fiap.roupas.exceptions;

public class InvalidIDException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidIDException(){
        super("Id inválido, tente novamente.");
    }

	public InvalidIDException(String msg, Throwable cause){
        super(msg, cause);
    }
}
