package br.com.pibsantalucia.vo;

import java.io.Serializable;

public class ViewUsuarioMembroVO implements Serializable {

	private static final long serialVersionUID = 7892154889442312840L;
	
	private long umeCodigo;
	private String umeSenha;
	private Boolean umeSenhaTemp;
	private long perCodigo;
	private long pesCodigo;
	private String pesNome;
	private String urlPhoto;
	private String token;
	
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
	
	public String getUrlPhoto() {
		return urlPhoto;
	}

	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getUmeCodigo() {
		return umeCodigo;
	}

	public void setUmeCodigo(long umeCodigo) {
		this.umeCodigo = umeCodigo;
	}
}
