package br.com.pibsantalucia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "TBL_CONTATO")
public class Contato implements Serializable {

	private static final long serialVersionUID = 3426419887913631483L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CTO_CODIGO", updatable = false, nullable = false)
	private long ctoCodigo;

	@Column(name = "CTO_DESCRICAOEMAIL")
	private String ctoDescricaoEmail;

	@Column(name = "CTO_DDD")
	private String ctoDdd;

	@Column(name = "CTO_NUMEROTELEFONE")
	private String ctoNumeroTelefone;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PES_CODIGO")
	private Pessoa pessoa;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TPC_CODIGO")
	private TipoContato tipoContato;

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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public TipoContato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}
}
