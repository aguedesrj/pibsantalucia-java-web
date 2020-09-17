package br.com.pibsantalucia.vo;

import java.io.Serializable;

public class ObitoVO implements Serializable {

	private static final long serialVersionUID = 6427157629155752941L;
	
	private long pesCodigo;
	private String obiDtObito;
	private String obiCausa;
	private String obiObservacao;
	private UsuarioMembroVO usuarioMembroVO;
	
	public long getPesCodigo() {
		return pesCodigo;
	}
	
	public void setPesCodigo(long pesCodigo) {
		this.pesCodigo = pesCodigo;
	}
	
	public String getObiDtObito() {
		return obiDtObito;
	}
	
	public void setObiDtObito(String obiDtObito) {
		this.obiDtObito = obiDtObito;
	}
	
	public String getObiCausa() {
		return obiCausa;
	}
	
	public void setObiCausa(String obiCausa) {
		this.obiCausa = obiCausa;
	}
	
	public String getObiObservacao() {
		return obiObservacao;
	}
	
	public void setObiObservacao(String obiObservacao) {
		this.obiObservacao = obiObservacao;
	}

	public UsuarioMembroVO getUsuarioMembroVO() {
		return usuarioMembroVO;
	}

	public void setUsuarioMembroVO(UsuarioMembroVO usuarioMembroVO) {
		this.usuarioMembroVO = usuarioMembroVO;
	}
}
