package br.com.pibsantalucia.vo;

import java.io.Serializable;

public class AnuncioVO implements Serializable {

	private static final long serialVersionUID = -7513829689896491588L;
	
	private long anuCodigo;
	private String anuDescricao;
	private String anuDtLimite;
	private UsuarioMembroVO usuarioMembroVO;
	
	public long getAnuCodigo() {
		return anuCodigo;
	}
	
	public void setAnuCodigo(long anuCodigo) {
		this.anuCodigo = anuCodigo;
	}
	
	public String getAnuDescricao() {
		return anuDescricao;
	}
	
	public void setAnuDescricao(String anuDescricao) {
		this.anuDescricao = anuDescricao;
	}
	
	public String getAnuDtLimite() {
		return anuDtLimite;
	}

	public void setAnuDtLimite(String anuDtLimite) {
		this.anuDtLimite = anuDtLimite;
	}

	public UsuarioMembroVO getUsuarioMembroVO() {
		return usuarioMembroVO;
	}
	
	public void setUsuarioMembroVO(UsuarioMembroVO usuarioMembroVO) {
		this.usuarioMembroVO = usuarioMembroVO;
	}
}
