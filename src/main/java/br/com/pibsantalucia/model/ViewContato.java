package br.com.pibsantalucia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "VIW_CONTATO")
public class ViewContato implements Serializable {

	private static final long serialVersionUID = -4785080980076334867L;

	@Id
	@Column(name = "CTO_CODIGO")
	private long ctoCodigo;

	@Column(name = "CTO_DESCRICAOEMAIL")
	private String ctoDescricaoEmail;

	@Column(name = "CTO_DDD")
	private String ctoDdd;

	@Column(name = "CTO_NUMEROTELEFONE")
	private String ctoNumeroTelefone;

	@Column(name = "PES_CODIGO")
	private long pesCodigo;

	@Column(name = "TPC_CODIGO")
	private int tpcCodigo;

	@Column(name = "TPC_DESCRICAO")
	private String tpcDescricao;

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

	public long getPesCodigo() {
		return pesCodigo;
	}

	public void setPesCodigo(long pesCodigo) {
		this.pesCodigo = pesCodigo;
	}

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
