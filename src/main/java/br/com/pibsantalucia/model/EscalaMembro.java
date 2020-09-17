package br.com.pibsantalucia.model;

import java.io.Serializable;

public class EscalaMembro implements Serializable {

	private static final long serialVersionUID = -1199906857295766746L;

	private long escCodigo;
	private long pesCodigo;

	public long getEscCodigo() {
		return escCodigo;
	}

	public void setEscCodigo(long escCodigo) {
		this.escCodigo = escCodigo;
	}

	public long getPesCodigo() {
		return pesCodigo;
	}

	public void setPesCodigo(long pesCodigo) {
		this.pesCodigo = pesCodigo;
	}
}
