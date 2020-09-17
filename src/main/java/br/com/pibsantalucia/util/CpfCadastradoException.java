package br.com.pibsantalucia.util;

/**
 * 
 * @author guedes
 *
 */
public class CpfCadastradoException extends Exception {

	private static final long serialVersionUID = -1345019059940429445L;

	public CpfCadastradoException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CpfCadastradoException(String message) {
		super(message);
	}	
	
	public CpfCadastradoException(Throwable cause) {
		super(cause);
	}	
	
	public CpfCadastradoException() {
		super();
	}
}
