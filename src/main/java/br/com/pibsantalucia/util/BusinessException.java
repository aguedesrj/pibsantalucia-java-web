package br.com.pibsantalucia.util;

/**
 * 
 * @author guedes
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = -8025567737460803932L;

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BusinessException(String message) {
		super(message);
	}	
	
	public BusinessException(Throwable cause) {
		super(cause);
	}	
	
	public BusinessException() {
		super();
	}
}
