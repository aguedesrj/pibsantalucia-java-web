package br.com.pibsantalucia.vo;

import java.io.Serializable;

public class TipoContatoVO implements Serializable {
	
	private static final long serialVersionUID = -7133578615248783762L;
	
	private int tpcCodigo;
	private String tpcDescricao;
	
	public int getTpcCodigo() {
		return tpcCodigo;
	}
	
	public void setTpcCodigo(int tpcCodigo) {
		this.tpcCodigo = tpcCodigo;
	}
	
	public String getTpcDescricao() {
		return tpcDescricao;
	}
	
	public void setTpcDescricao(String tpcDescricao) {
		this.tpcDescricao = tpcDescricao;
	}
}
