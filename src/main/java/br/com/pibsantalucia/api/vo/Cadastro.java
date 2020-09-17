package br.com.pibsantalucia.api.vo;

import java.io.Serializable;

public class Cadastro implements Serializable {

	private static final long serialVersionUID = 2900137735632670747L;

	private String cpfOrEmail;

	public String getCpfOrEmail() {
		return cpfOrEmail;
	}

	public void setCpfOrEmail(String cpfOrEmail) {
		this.cpfOrEmail = cpfOrEmail;
	}
}
