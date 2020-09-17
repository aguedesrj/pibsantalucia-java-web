package br.com.pibsantalucia.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "TBL_DEVICE_MOBILE")
public class DeviceMobile implements Serializable {

	private static final long serialVersionUID = -9019429141988126693L;

	@Id
	@Column(name = "DEM_ID", updatable = false, nullable = false)
	private String demId;

	@Column(name = "DEM_OS")
	private String demOs;

	@Column(name = "DEM_VERSAO")
	private String demVersao;

	@Column(name = "DEM_TOKENFIREBASE")
	private String demTokenFirebase;
	
	@Column(name = "DEM_DTCADASTRO")
	private Calendar demDtCadastro;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PES_CODIGO")
	private Pessoa pessoa;

	public String getDemId() {
		return demId;
	}

	public void setDemId(String demId) {
		this.demId = demId;
	}

	public String getDemOs() {
		return demOs;
	}

	public void setDemOs(String demOs) {
		this.demOs = demOs;
	}

	public String getDemVersao() {
		return demVersao;
	}

	public void setDemVersao(String demVersao) {
		this.demVersao = demVersao;
	}

	public String getDemTokenFirebase() {
		return demTokenFirebase;
	}

	public void setDemTokenFirebase(String demTokenFirebase) {
		this.demTokenFirebase = demTokenFirebase;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Calendar getDemDtCadastro() {
		return demDtCadastro;
	}

	public void setDemDtCadastro(Calendar demDtCadastro) {
		this.demDtCadastro = demDtCadastro;
	}
	
}
