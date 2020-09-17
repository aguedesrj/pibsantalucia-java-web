package br.com.pibsantalucia.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "VIW_USUARIO_MEMBRO")
public class ViewUsuarioMembro implements Serializable {

	private static final long serialVersionUID = 4226939660109782075L;

	@Id
	@Column(name = "UME_CODIGO")
	private long umeCodigo;

	@Column(name = "UME_DTCADASTRO")
	private Calendar usuDtCadastro;

	@Column(name = "UME_SENHA")
	private String umeSenha;

	@Column(name = "UME_SENHA_TEMP")
	private Boolean umeSenhaTemp;

	@Column(name = "PER_CODIGO")
	private long perCodigo;
	
	@Column(name = "PER_NOME")
	private String perNome;
	
	@Column(name = "PES_CODIGO")
	private long pesCodigo;
	
	@Column(name = "PES_NOME")
	private String pesNome;
	
	@Column(name = "PES_NAMEPHOTO")
	private String pesNamePhoto;

	public long getUmeCodigo() {
		return umeCodigo;
	}

	public void setUmeCodigo(long umeCodigo) {
		this.umeCodigo = umeCodigo;
	}

	public Calendar getUsuDtCadastro() {
		return usuDtCadastro;
	}

	public void setUsuDtCadastro(Calendar usuDtCadastro) {
		this.usuDtCadastro = usuDtCadastro;
	}

	public String getUmeSenha() {
		return umeSenha;
	}

	public void setUmeSenha(String umeSenha) {
		this.umeSenha = umeSenha;
	}

	public Boolean getUmeSenhaTemp() {
		return umeSenhaTemp;
	}

	public void setUmeSenhaTemp(Boolean umeSenhaTemp) {
		this.umeSenhaTemp = umeSenhaTemp;
	}

	public long getPerCodigo() {
		return perCodigo;
	}

	public void setPerCodigo(long perCodigo) {
		this.perCodigo = perCodigo;
	}

	public String getPerNome() {
		return perNome;
	}

	public void setPerNome(String perNome) {
		this.perNome = perNome;
	}

	public long getPesCodigo() {
		return pesCodigo;
	}

	public void setPesCodigo(long pesCodigo) {
		this.pesCodigo = pesCodigo;
	}

	public String getPesNome() {
		return pesNome;
	}

	public void setPesNome(String pesNome) {
		this.pesNome = pesNome;
	}

	public String getPesNamePhoto() {
		return pesNamePhoto;
	}

	public void setPesNamePhoto(String pesNamePhoto) {
		this.pesNamePhoto = pesNamePhoto;
	}
}