package br.com.fiap.roupas.exceptions;

public class ConnectionRefusedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConnectionRefusedException(){
        super("Erro ao efetuar sua solicitação, tente novamente mais tarde");
    }

	public ConnectionRefusedException(String msg, Throwable cause){
        super(msg, cause);
    }

}
