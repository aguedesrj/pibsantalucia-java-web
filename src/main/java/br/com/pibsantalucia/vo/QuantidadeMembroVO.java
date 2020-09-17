package br.com.pibsantalucia.vo;

import java.io.Serializable;

public class QuantidadeMembroVO implements Serializable {

	private static final long serialVersionUID = -2243498355343284867L;

	private int percentualFemninino;
	private int percentualMasculino;
	private int quantidadeMembros;
	
	public int getPercentualFemninino() {
		return percentualFemninino;
	}
	
	public void setPercentualFemninino(int percentualFemninino) {
		this.percentualFemninino = percentualFemninino;
	}
	
	public int getPercentualMasculino() {
		return percentualMasculino;
	}
	
	public void setPercentualMasculino(int percentualMasculino) {
		this.percentualMasculino = percentualMasculino;
	}
	
	public int getQuantidadeMembros() {
		return quantidadeMembros;
	}
	
	public void setQuantidadeMembros(int quantidadeMembros) {
		this.quantidadeMembros = quantidadeMembros;
	}
}
