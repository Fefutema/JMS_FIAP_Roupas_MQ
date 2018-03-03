package br.com.fiap.roupas.exceptions;

public class CupomNotSendException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CupomNotSendException(){
        super("Erro ao efetuar o envio do cupom, tente novamente mais tarde");
    }

	public CupomNotSendException(String msg, Throwable cause){
        super(msg, cause);
    }


}
