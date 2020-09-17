package br.com.pibsantalucia.api.vo;

import java.io.Serializable;

public class TrocaSenha implements Serializable {

	private static final long serialVersionUID = 5000659374930905638L;
	
	private String cpfOrEmail;
	private String passwordNew;
	private String passwordNewConfirmation;
	
	public String getCpfOrEmail() {
		return cpfOrEmail;
	}
	
	public void setCpfOrEmail(String cpfOrEmail) {
		this.cpfOrEmail = cpfOrEmail;
	}
	
	public String getPasswordNew() {
		return passwordNew;
	}
	
	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}
	
	public String getPasswordNewConfirmation() {
		return passwordNewConfirmation;
	}
	
	public void setPasswordNewConfirmation(String passwordNewConfirmation) {
		this.passwordNewConfirmation = passwordNewConfirmation;
	}
}
