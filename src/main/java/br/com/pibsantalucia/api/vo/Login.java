package br.com.pibsantalucia.api.vo;

import java.io.Serializable;

public class Login implements Serializable {

	private static final long serialVersionUID = 3353466151567628726L;
	
	private String cpfOrEmail;
	private String password;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpfOrEmail() {
		return cpfOrEmail;
	}

	public void setCpfOrEmail(String cpfOrEmail) {
		this.cpfOrEmail = cpfOrEmail;
	}
}
