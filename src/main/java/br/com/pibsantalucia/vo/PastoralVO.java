package br.com.pibsantalucia.vo;

import java.io.Serializable;

public class PastoralVO implements Serializable {

	private static final long serialVersionUID = -2172761701027233510L;
	
	private long pasCodigo;
	private String pasDtReferencia;
	private String pasPastoral;
	private UsuarioMembroVO usuarioMembroVO;
	
	public long getPasCodigo() {
		return pasCodigo;
	}
	
	public void setPasCodigo(long pasCodigo) {
		this.pasCodigo = pasCodigo;
	}
	
	public String getPasDtReferencia() {
		return pasDtReferencia;
	}
	
	public void setPasDtReferencia(String pasDtReferencia) {
		this.pasDtReferencia = pasDtReferencia;
	}
	
	public String getPasPastoral() {
		return pasPastoral;
	}
	
	public void setPasPastoral(String pasPastoral) {
		this.pasPastoral = pasPastoral;
	}
	
	public UsuarioMembroVO getUsuarioMembroVO() {
		return usuarioMembroVO;
	}
	
	public void setUsuarioMembroVO(UsuarioMembroVO usuarioMembroVO) {
		this.usuarioMembroVO = usuarioMembroVO;
	}
}
