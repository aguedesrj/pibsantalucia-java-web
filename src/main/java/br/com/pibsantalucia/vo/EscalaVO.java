package br.com.pibsantalucia.vo;

import java.io.Serializable;
import java.util.List;

public class EscalaVO implements Serializable {

	private static final long serialVersionUID = 6433593262320790926L;

	private long escCodigo;
	private String escDtEscala;
	private int escFlgTipo;
	private String escDescricao;
	private int escAnoMes;
	private List<ViewMembroVO> lista;
	
	public long getEscCodigo() {
		return escCodigo;
	}
	
	public void setEscCodigo(long escCodigo) {
		this.escCodigo = escCodigo;
	}
	
	public String getEscDtEscala() {
		return escDtEscala;
	}
	
	public void setEscDtEscala(String escDtEscala) {
		this.escDtEscala = escDtEscala;
	}
	
	public int getEscFlgTipo() {
		return escFlgTipo;
	}
	
	public void setEscFlgTipo(int escFlgTipo) {
		this.escFlgTipo = escFlgTipo;
	}
	
	public String getEscDescricao() {
		return escDescricao;
	}
	
	public void setEscDescricao(String escDescricao) {
		this.escDescricao = escDescricao;
	}
	
	public int getEscAnoMes() {
		return escAnoMes;
	}

	public void setEscAnoMes(int escAnoMes) {
		this.escAnoMes = escAnoMes;
	}

	public List<ViewMembroVO> getLista() {
		return lista;
	}
	
	public void setLista(List<ViewMembroVO> lista) {
		this.lista = lista;
	}
}
