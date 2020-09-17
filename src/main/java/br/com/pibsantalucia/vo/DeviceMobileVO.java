package br.com.pibsantalucia.vo;

import java.io.Serializable;

public class DeviceMobileVO implements Serializable {

	private static final long serialVersionUID = -8933938068679277426L;

	private String demId;
	private String demOs;
	private String demVersao;
	private String demTokenFirebase;
	private long pesCodigo;
	
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

	public long getPesCodigo() {
		return pesCodigo;
	}

	public void setPesCodigo(long pesCodigo) {
		this.pesCodigo = pesCodigo;
	}
}
