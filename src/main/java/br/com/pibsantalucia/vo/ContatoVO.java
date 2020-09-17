package br.com.pibsantalucia.vo;

import java.io.Serializable;

public class ContatoVO implements Serializable {
	
	private static final long serialVersionUID = -4536151725927176906L;
	
	private long ctoCodigo;
	private boolean novoContato;
	private boolean excluidoContato;
	private String ctoDescricaoEmail;
	private String ctoDdd;
	private String ctoNumeroTelefone;
	private long pesCodigo;
	private TipoContatoVO tipoContatoVO;
	
	public boolean isNovoContato() {
		return novoContato;
	}

	public void setNovoContato(boolean novoContato) {
		this.novoContato = novoContato;
	}

	public long getCtoCodigo() {
		return ctoCodigo;
	}
	
	public void setCtoCodigo(long ctoCodigo) {
		this.ctoCodigo = ctoCodigo;
	}
	
	public String getCtoDescricaoEmail() {
		return ctoDescricaoEmail;
	}
	
	public void setCtoDescricaoEmail(String ctoDescricaoEmail) {
		this.ctoDescricaoEmail = ctoDescricaoEmail;
	}
	
	public String getCtoDdd() {
		return ctoDdd;
	}
	
	public void setCtoDdd(String ctoDdd) {
		this.ctoDdd = ctoDdd;
	}
	
	public String getCtoNumeroTelefone() {
		return ctoNumeroTelefone;
	}
	
	public void setCtoNumeroTelefone(String ctoNumeroTelefone) {
		this.ctoNumeroTelefone = ctoNumeroTelefone;
	}
	
	public TipoContatoVO getTipoContatoVO() {
		return tipoContatoVO;
	}
	
	public void setTipoContatoVO(TipoContatoVO tipoContatoVO) {
		this.tipoContatoVO = tipoContatoVO;
	}

	public boolean isExcluidoContato() {
		return excluidoContato;
	}

	public void setExcluidoContato(boolean excluidoContato) {
		this.excluidoContato = excluidoContato;
	}

	public long getPesCodigo() {
		return pesCodigo;
	}

	public void setPesCodigo(long pesCodigo) {
		this.pesCodigo = pesCodigo;
	}
}
