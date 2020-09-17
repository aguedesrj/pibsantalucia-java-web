package br.com.pibsantalucia.vo;

import java.io.Serializable;

public class EscalaPeriodoVO implements Serializable {

	private static final long serialVersionUID = -5842101264737115896L;
	
	private String escDtEscala;
	private String escDescricao;
	private String escFlgTipo;
	
	public String getEscDtEscala() {
		return escDtEscala;
	}
	
	public void setEscDtEscala(String escDtEscala) {
		this.escDtEscala = escDtEscala;
	}
	
	public String getEscDescricao() {
		return escDescricao;
	}
	
	public void setEscDescricao(String escDescricao) {
		this.escDescricao = escDescricao;
	}
	
	public String getEscFlgTipo() {
		return escFlgTipo;
	}
	
	public void setEscFlgTipo(String escFlgTipo) {
		this.escFlgTipo = escFlgTipo;
	}
}