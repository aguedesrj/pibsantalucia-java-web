package br.com.pibsantalucia.vo;

import java.io.Serializable;

public class EscalaMembroVO implements Serializable {

	private static final long serialVersionUID = 745868164664314975L;
	
	private long pesCodigo;

	public long getPesCodigo() {
		return pesCodigo;
	}

	public void setPesCodigo(long pesCodigo) {
		this.pesCodigo = pesCodigo;
	}
}
