package br.com.pibsantalucia.vo;

import java.io.Serializable;
import java.util.List;

public class EscalaMesesVO implements Serializable {

	private static final long serialVersionUID = 6030609152188802962L;

	private int mes;
	private String mesDescricao;
	private int ano;
	private List<EscalaVO> listaEscalas;
	
	public int getMes() {
		return mes;
	}
	
	public void setMes(int mes) {
		this.mes = mes;
	}
	
	public String getMesDescricao() {
		return mesDescricao;
	}
	
	public void setMesDescricao(String mesDescricao) {
		this.mesDescricao = mesDescricao;
	}
	
	public int getAno() {
		return ano;
	}
	
	public void setAno(int ano) {
		this.ano = ano;
	}

	public List<EscalaVO> getListaEscalas() {
		return listaEscalas;
	}

	public void setListaEscalas(List<EscalaVO> listaEscalas) {
		this.listaEscalas = listaEscalas;
	}
}
