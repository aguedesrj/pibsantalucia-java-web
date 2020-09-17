package br.com.pibsantalucia.util;

public class JwtExpiredException extends Exception {

	private static final long serialVersionUID = -737354370854258722L;

	public JwtExpiredException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public JwtExpiredException(String message) {
		super(message);
	}	
	
	public JwtExpiredException(Throwable cause) {
		super(cause);
	}	
	
	public JwtExpiredException() {
		super();
	}
}
