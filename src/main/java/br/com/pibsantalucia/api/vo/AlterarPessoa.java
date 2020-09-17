package br.com.pibsantalucia.api.vo;

import java.io.Serializable;

public class AlterarPessoa implements Serializable {

	private static final long serialVersionUID = -5793023566335218425L;

	private long pesCodigo;

	public long getPesCodigo() {
		return pesCodigo;
	}

	public void setPesCodigo(long pesCodigo) {
		this.pesCodigo = pesCodigo;
	}
}