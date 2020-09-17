package br.com.pibsantalucia.vo;

import java.io.Serializable;

public class SendPushNotificationVO implements Serializable {

	private static final long serialVersionUID = -8527315835254595689L;
	private int pesCodigo;
	private String demTokenFirebase;
	
	public int getPesCodigo() {
		return pesCodigo;
	}

	public void setPesCodigo(int pesCodigo) {
		this.pesCodigo = pesCodigo;
	}

	public String getDemTokenFirebase() {
		return demTokenFirebase;
	}
	
	public void setDemTokenFirebase(String demTokenFirebase) {
		this.demTokenFirebase = demTokenFirebase;
	}
}
