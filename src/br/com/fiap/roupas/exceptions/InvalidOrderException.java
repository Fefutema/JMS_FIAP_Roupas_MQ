package br.com.fiap.roupas.exceptions;

public class InvalidOrderException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidOrderException(){
        super("Pedodo inválido, tente novamente.");
    }

	public InvalidOrderException(String msg, Throwable cause){
        super(msg, cause);
    }

}
